package com.example.library.controller;

import com.example.library.service.ContactMessageService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

    private final ContactMessageService contactMessageService;

    public ContactController(
            ContactMessageService contactMessageService) {

        this.contactMessageService = contactMessageService;
    }

    @GetMapping("/contact")
    public String contactPage(Model model) {

        return "contact";
    }

    @PostMapping("/contact")
    public String submitContact(
            @RequestParam String subject,
            @RequestParam String message,
            Authentication authentication,
            Model model) {

        try {

            contactMessageService.submitMessage(
                    authentication.getName(),
                    subject,
                    message
            );

            return "redirect:/contact?success";

        } catch (IllegalArgumentException e) {

            model.addAttribute("error", e.getMessage());

            model.addAttribute("subject", subject);
            model.addAttribute("message", message);

            return "contact";
        }
    }
    
    @GetMapping("/my-queries")
    public String myQueries(
            Authentication authentication,
            Model model) 
    {

        model.addAttribute(
                "messages",
                contactMessageService.getUserMessages(
                        authentication.getName()
                )
        );

        return "my-queries";
    }
}