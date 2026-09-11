package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminBookController {

    private final BookService bookService;

    public AdminBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/admin/books")
    public String books(Model model) {

        model.addAttribute(
                "books",
                bookService.getAllBooks()
        );

        return "admin-books";
    }

    @GetMapping("/admin/books/add")
    public String addBookForm(Model model) {

        model.addAttribute("book", new Book());
        model.addAttribute("editMode", false);

        return "admin-book-form";
    }

    @PostMapping("/admin/books/add")
    public String addBook(
            @ModelAttribute("book") Book book,
            Model model) {

        try {

            bookService.addBook(book);

            return "redirect:/admin/books?added";

        } catch (IllegalArgumentException e) {

            model.addAttribute("error", e.getMessage());
            model.addAttribute("editMode", false);

            return "admin-book-form";
        }
    }

    @GetMapping("/admin/books/{id}/edit")
    public String editBook(
            @PathVariable Long id,
            Model model) {

        try {

            Book book = bookService.getBookById(id);

            model.addAttribute("book", book);
            model.addAttribute("editMode", true);
            model.addAttribute("bookId", id);

            return "admin-book-form";

        } catch (IllegalArgumentException e) {

            return "redirect:/admin/books?error="
                    + java.net.URLEncoder.encode(
                            e.getMessage(),
                            java.nio.charset.StandardCharsets.UTF_8
                    );
        }
    }

    @PostMapping("/admin/books/{id}/edit")
    public String updateBook(
            @PathVariable Long id,
            @ModelAttribute("book") Book book,
            Model model) {

        try {

            bookService.updateBook(id, book);

            return "redirect:/admin/books?updated";

        } catch (IllegalArgumentException e) {

            model.addAttribute("error", e.getMessage());
            model.addAttribute("editMode", true);
            model.addAttribute("bookId", id);

            return "admin-book-form";
        }
    }

    @PostMapping("/admin/books/{id}/delete")
    public String deleteBook(
            @PathVariable Long id) {

        try {

            bookService.deleteBook(id);

            return "redirect:/admin/books?deleted";

        } catch (IllegalArgumentException e) {

            return "redirect:/admin/books?error="
                    + java.net.URLEncoder.encode(
                            e.getMessage(),
                            java.nio.charset.StandardCharsets.UTF_8
                    );
        }
    }
}