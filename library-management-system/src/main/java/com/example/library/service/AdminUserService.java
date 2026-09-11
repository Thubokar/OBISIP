package com.example.library.service;

import com.example.library.entity.User;
import com.example.library.repository.FineRepository;
import com.example.library.repository.IssueRecordRepository;
import com.example.library.repository.ReservationRepository;
import com.example.library.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminUserService {

    private final UserRepository userRepository;
    private final IssueRecordRepository issueRecordRepository;
    private final FineRepository fineRepository;
    private final ReservationRepository reservationRepository;

    public AdminUserService(
            UserRepository userRepository,
            IssueRecordRepository issueRecordRepository,
            FineRepository fineRepository,
            ReservationRepository reservationRepository) {

        this.userRepository = userRepository;
        this.issueRecordRepository = issueRecordRepository;
        this.fineRepository = fineRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found"));
    }

    @Transactional
    public void toggleUserStatus(Long id) {

        User user = getUserById(id);

        if (user.getRole() == User.Role.ADMIN) {
            throw new IllegalStateException(
                    "Admin accounts cannot be disabled"
            );
        }

        user.setEnabled(!user.isEnabled());

        userRepository.save(user);
    }

    @Transactional
    public void updateUser(
            Long id,
            String name,
            String email,
            User.Role role) {

        User user = getUserById(id);

        String normalizedEmail = email.trim().toLowerCase();

        if (userRepository.existsByEmailAndIdNot(
                normalizedEmail,
                id)) {

            throw new IllegalStateException(
                    "Another user already has this email address"
            );
        }

        user.setName(name.trim());
        user.setEmail(normalizedEmail);
        user.setRole(role);

        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {

        User user = getUserById(id);

        if (user.getRole() == User.Role.ADMIN) {
            throw new IllegalStateException(
                    "Admin accounts cannot be deleted"
            );
        }

        if (issueRecordRepository.existsByUser(user)) {
            throw new IllegalStateException(
                    "User cannot be deleted because they have library issue history"
            );
        }

        if (fineRepository.existsByUser(user)) {
            throw new IllegalStateException(
                    "User cannot be deleted because they have fine records"
            );
        }

        if (reservationRepository.existsByUser(user)) {
            throw new IllegalStateException(
                    "User cannot be deleted because they have reservation history"
            );
        }

        userRepository.delete(user);
    }
}