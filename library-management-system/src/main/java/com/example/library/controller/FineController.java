package com.example.library.controller;

import com.example.library.entity.Fine;
import com.example.library.entity.IssueRecord;
import com.example.library.entity.User;
import com.example.library.repository.UserRepository;
import com.example.library.service.FineService;
import com.example.library.service.IssueService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FineController {

    private final FineService fineService;
    private final UserRepository userRepository;
    private final IssueService issueService;

    public FineController(
            FineService fineService,
            UserRepository userRepository,
            IssueService issueService) {

        this.fineService = fineService;
        this.userRepository = userRepository;
        this.issueService = issueService;
    }

    @GetMapping("/user/fines")
    public String userFines(
            Authentication authentication,
            Model model) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(
                email.trim().toLowerCase()
        ).orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        // Get books that are currently issued to the user
        List<IssueRecord> issuedBooks =
                issueService.getUserIssuedBooks(email);

        // Create or update fines for overdue books
        fineService.updateOverdueFines(issuedBooks);

        // Get all fines belonging to this user
        List<Fine> fines = fineService.getUserFines(user);

        model.addAttribute("fines", fines);

        return "fines";
    }
}