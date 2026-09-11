package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.entity.IssueRecord;
import com.example.library.entity.Reservation;
import com.example.library.entity.User;
import com.example.library.repository.IssueRecordRepository;
import com.example.library.repository.ReservationRepository;
import com.example.library.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class IssueService {

    private static final int LOAN_DAYS = 14;

    private final IssueRecordRepository issueRecordRepository;
    private final UserRepository userRepository;
    private final BookService bookService;
    private final FineService fineService;
    private final ReservationRepository reservationRepository;

    public IssueService(
            IssueRecordRepository issueRecordRepository,
            UserRepository userRepository,
            BookService bookService,
            FineService fineService,
            ReservationRepository reservationRepository) {

        this.issueRecordRepository = issueRecordRepository;
        this.userRepository = userRepository;
        this.bookService = bookService;
        this.fineService = fineService;
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    public IssueRecord issueBook(String email, Long bookId) {

        User user = userRepository.findByEmail(
                email.trim().toLowerCase()
        ).orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        Book book = bookService.getBookById(bookId);

        if (issueRecordRepository
                .existsByUserAndBookAndReturnedFalse(user, book)) {

            throw new IllegalStateException(
                    "You already have this book issued"
            );
        }

        if (book.getQuantity() <= 0) {
            throw new IllegalStateException(
                    "Book is currently unavailable"
            );
        }

        LocalDate issueDate = LocalDate.now();
        LocalDate dueDate = issueDate.plusDays(LOAN_DAYS);

        IssueRecord issueRecord = new IssueRecord();

        issueRecord.setUser(user);
        issueRecord.setBook(book);
        issueRecord.setIssueDate(issueDate);
        issueRecord.setDueDate(dueDate);
        issueRecord.setReturned(false);

        bookService.decreaseQuantity(bookId);

        return issueRecordRepository.save(issueRecord);
    }

    public List<IssueRecord> getUserIssuedBooks(String email) {

        User user = userRepository.findByEmail(
                email.trim().toLowerCase()
        ).orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        return issueRecordRepository
                .findByUserAndReturnedFalse(user);
    }

    public List<IssueRecord> getAllIssuedBooks() {

        return issueRecordRepository.findByReturnedFalse();
    }

    @Transactional
    public void returnBook(String email, Long issueId) {

        User user = userRepository.findByEmail(
                email.trim().toLowerCase()
        ).orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        IssueRecord issueRecord =
                issueRecordRepository
                        .findByIdAndUser(issueId, user)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Issue record not found"
                                ));

        if (issueRecord.isReturned()) {

            throw new IllegalStateException(
                    "This book has already been returned"
            );
        }

        issueRecord.setReturnDate(LocalDate.now());
        issueRecord.setReturned(true);

        /*
         * Increase the available quantity
         * because the book has been returned.
         */
        bookService.increaseQuantity(
                issueRecord.getBook().getId()
        );

        /*
         * Save the returned issue record first.
         * FineService uses the return date
         * to calculate the final fine.
         */
        issueRecordRepository.save(issueRecord);

        /*
         * Create a fine only if the book was
         * returned after the due date.
         */
        fineService.createFineIfNeeded(issueRecord);

        /*
         * Find the oldest pending reservation
         * for this returned book.
         */
        Reservation reservation =
                reservationRepository
                        .findFirstByBookAndFulfilledFalseOrderByReservationDateAsc(
                                issueRecord.getBook()
                        )
                        .orElse(null);

        /*
         * If a user is waiting for this book,
         * automatically issue the returned book
         * to that user.
         */
        if (reservation != null) {

            issueBook(
                    reservation.getUser().getEmail(),
                    reservation.getBook().getId()
            );

            /*
             * The reservation has now been fulfilled
             * because the waiting user received the book.
             */
            reservation.setFulfilled(true);

            reservationRepository.save(reservation);
        }
    }

    public double calculateFine(IssueRecord issueRecord) {

        return fineService.calculateFine(issueRecord);
    }
}