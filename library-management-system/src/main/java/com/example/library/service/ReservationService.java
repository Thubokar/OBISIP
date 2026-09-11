package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.entity.Reservation;
import com.example.library.entity.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.ReservationRepository;
import com.example.library.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public ReservationService(
            ReservationRepository reservationRepository,
            UserRepository userRepository,
            BookRepository bookRepository) {

        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public Reservation reserveBook(String email, Long bookId) {

        User user = userRepository.findByEmail(
                email.trim().toLowerCase()
        ).orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Book not found"));

        // A user cannot reserve the same book twice
        if (reservationRepository
                .existsByUserAndBookAndFulfilledFalse(user, book)) {

            throw new IllegalStateException(
                    "You already have an active reservation for this book"
            );
        }

        // Reservation is intended for unavailable books
        if (book.getQuantity() > 0) {

            throw new IllegalStateException(
                    "This book is currently available. You can issue it directly."
            );
        }

        Reservation reservation =
                new Reservation(user, book);

        return reservationRepository.save(reservation);
    }

    public List<Reservation> getUserReservations(String email) {

        User user = userRepository.findByEmail(
                email.trim().toLowerCase()
        ).orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        return reservationRepository.findByUser(user);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getPendingReservations() {
        return reservationRepository.findByFulfilled(false);
    }

    @Transactional
    public void markAsFulfilled(Long reservationId) {

        Reservation reservation =
                reservationRepository.findById(reservationId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Reservation not found"
                                ));

        reservation.setFulfilled(true);

        reservationRepository.save(reservation);
    }
}