package com.example.library.service;

import com.example.library.entity.ContactMessage;
import com.example.library.entity.User;
import com.example.library.repository.ContactMessageRepository;
import com.example.library.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;
    private final UserRepository userRepository;

    public ContactMessageService(
            ContactMessageRepository contactMessageRepository,
            UserRepository userRepository) {

        this.contactMessageRepository = contactMessageRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ContactMessage submitMessage(
            String email,
            String subject,
            String message) {

        User user = userRepository.findByEmail(
                email.trim().toLowerCase()
        ).orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        if (subject == null || subject.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Subject is required"
            );
        }

        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Message is required"
            );
        }

        ContactMessage contactMessage =
                new ContactMessage(
                        user,
                        subject.trim(),
                        message.trim()
                );

        return contactMessageRepository.save(contactMessage);
    }

    public List<ContactMessage> getUserMessages(String email) {

        User user = userRepository.findByEmail(
                email.trim().toLowerCase()
        ).orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        return contactMessageRepository
                .findByUserOrderByCreatedAtDesc(user);
    }

    public List<ContactMessage> getAllMessages() {

        return contactMessageRepository
                .findAllByOrderByCreatedAtDesc();
    }

    public List<ContactMessage> getMessagesByStatus(
            boolean resolved) {

        return contactMessageRepository
                .findByResolvedOrderByCreatedAtDesc(resolved);
    }

    @Transactional
    public void markAsResolved(Long id) {

        ContactMessage message =
                contactMessageRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Contact message not found"
                                ));

        message.setResolved(true);

        contactMessageRepository.save(message);
    }
}