package com.example.library.repository;

import com.example.library.entity.ContactMessage;
import com.example.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactMessageRepository
        extends JpaRepository<ContactMessage, Long> {

    List<ContactMessage> findByUserOrderByCreatedAtDesc(User user);

    List<ContactMessage> findAllByOrderByCreatedAtDesc();

    List<ContactMessage> findByResolvedOrderByCreatedAtDesc(boolean resolved);
}