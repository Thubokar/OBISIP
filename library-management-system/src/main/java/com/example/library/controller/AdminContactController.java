package com.example.library.controller;

import com.example.library.service.ContactMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminContactController {

    private final ContactMessageService contactMessageService;

    public AdminContactController(
            ContactMessageService contactMessageService) {

        this.contactMessageService = contactMessageService;
    }

    @GetMapping("/admin/contact")
    public String contactMessages(Model model) {

        model.addAttribute(
                "messages",
                contactMessageService.getAllMessages()
        );

        return "admin-contact";
    }

    @PostMapping("/admin/contact/{id}/resolve")
    public String markAsResolved(@PathVariable Long id) {

        contactMessageService.markAsResolved(id);

        return "redirect:/admin/contact?resolved";
    }
}