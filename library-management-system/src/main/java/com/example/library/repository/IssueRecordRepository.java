package com.example.library.repository;

import com.example.library.entity.IssueRecord;
import com.example.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IssueRecordRepository extends JpaRepository<IssueRecord, Long> {

    List<IssueRecord> findByUserAndReturnedFalse(User user);

    List<IssueRecord> findByReturnedFalse();

    Optional<IssueRecord> findByIdAndUser(Long id, User user);

    boolean existsByUserAndBookAndReturnedFalse(
            User user,
            com.example.library.entity.Book book
    );

    boolean existsByUser(User user);
}