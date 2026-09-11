
package com.example.library.config;

import com.example.library.repository.BookRepository;
import com.example.library.repository.ContactMessageRepository;
import com.example.library.repository.FineRepository;
import com.example.library.repository.IssueRecordRepository;
import com.example.library.repository.ReservationRepository;
import com.example.library.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final BookRepository bookRepository;
    private final IssueRecordRepository issueRecordRepository;
    private final UserRepository userRepository;
    private final FineRepository fineRepository;
    private final ReservationRepository reservationRepository;
    private final ContactMessageRepository contactMessageRepository;

    public DashboardController(
            BookRepository bookRepository,
            IssueRecordRepository issueRecordRepository,
            UserRepository userRepository,
            FineRepository fineRepository,
            ReservationRepository reservationRepository,
            ContactMessageRepository contactMessageRepository) {

        this.bookRepository = bookRepository;
        this.issueRecordRepository = issueRecordRepository;
        this.userRepository = userRepository;
        this.fineRepository = fineRepository;
        this.reservationRepository = reservationRepository;
        this.contactMessageRepository = contactMessageRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(
            Authentication authentication,
            Model model) {

        // Logged-in user's email
        model.addAttribute(
                "email",
                authentication.getName()
        );

        // Current dashboard statistics
        model.addAttribute(
                "totalBooks",
                bookRepository.count()
        );

        model.addAttribute(
                "issuedBooks",
                issueRecordRepository
                        .findByReturnedFalse()
                        .size()
        );

        model.addAttribute(
                "totalUsers",
                userRepository.count()
        );

        model.addAttribute(
                "unpaidFines",
                fineRepository
                        .findByPaid(false)
                        .size()
        );

        model.addAttribute(
                "pendingReservations",
                reservationRepository
                        .findByFulfilled(false)
                        .size()
        );

        model.addAttribute(
                "pendingQueries",
                contactMessageRepository
                        .findByResolvedOrderByCreatedAtDesc(false)
                        .size()
        );

        return "dashboard";
    }
}
