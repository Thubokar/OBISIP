package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.service.BookService;
import com.example.library.service.IssueService;
import com.example.library.service.ReservationService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;
    private final IssueService issueService;
    private final ReservationService reservationService;

    public BookController(
            BookService bookService,
            IssueService issueService,
            ReservationService reservationService) {

        this.bookService = bookService;
        this.issueService = issueService;
        this.reservationService = reservationService;
    }

    @GetMapping("/books")
    public String books(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category,
            Model model) {

        List<Book> books;

        if (keyword != null && !keyword.trim().isEmpty()) {

            books = bookService.searchBooks(keyword);

        } else if (category != null && !category.trim().isEmpty()) {

            books = bookService.getBooksByCategory(category);

        } else {

            books = bookService.getAllBooks();
        }

        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);
        model.addAttribute("category", category);

        return "books";
    }

    @PostMapping("/books/{id}/issue")
    public String issueBook(
            @PathVariable Long id,
            Authentication authentication) {

        try {

            issueService.issueBook(
                    authentication.getName(),
                    id
            );

            return "redirect:/my-books?issued";

        } catch (IllegalStateException | IllegalArgumentException e) {

            return "redirect:/books?error=" +
                    URLEncoder.encode(
                            e.getMessage(),
                            StandardCharsets.UTF_8
                    );
        }
    }

    @PostMapping("/books/{id}/reserve")
    public String reserveBook(
            @PathVariable Long id,
            Authentication authentication) {

        try {

            reservationService.reserveBook(
                    authentication.getName(),
                    id
            );

            return "redirect:/books?reserved";

        } catch (IllegalStateException | IllegalArgumentException e) {

            return "redirect:/books?error=" +
                    URLEncoder.encode(
                            e.getMessage(),
                            StandardCharsets.UTF_8
                    );
        }
    }
}