package com.example.library.controller;

import com.example.library.entity.IssueRecord;
import com.example.library.service.IssueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminIssueController {

    private final IssueService issueService;

    public AdminIssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping("/admin/issued-books")
    public String issuedBooks(Model model) {

        List<IssueRecord> issues =
                issueService.getAllIssuedBooks();

        model.addAttribute("issues", issues);

        return "admin-issued-books";
    }
}