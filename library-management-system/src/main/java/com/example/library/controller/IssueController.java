package com.example.library.controller;

import com.example.library.entity.IssueRecord;
import com.example.library.service.IssueService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping("/my-books")
    public String myBooks(
            Authentication authentication,
            Model model) {

        List<IssueRecord> issues =
                issueService.getUserIssuedBooks(
                        authentication.getName()
                );

        model.addAttribute("issues", issues);

        return "my-books";
    }

    @PostMapping("/my-books/{id}/return")
    public String returnBook(
            @PathVariable Long id,
            Authentication authentication) {

        try {

            issueService.returnBook(
                    authentication.getName(),
                    id
            );

            return "redirect:/my-books?returned";

        } catch (IllegalArgumentException |
                 IllegalStateException e) {

            return "redirect:/my-books?error=" +
                    java.net.URLEncoder.encode(
                            e.getMessage(),
                            java.nio.charset.StandardCharsets.UTF_8
                    );
        }
    }
}