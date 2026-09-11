package com.example.library.repository;

import com.example.library.entity.Fine;
import com.example.library.entity.User;
import com.example.library.entity.IssueRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

public interface FineRepository extends JpaRepository<Fine, Long> {

    List<Fine> findByUser(User user);

    List<Fine> findByPaid(boolean paid);

    @Query("SELECT f FROM Fine f WHERE f.issueRecord = :issueRecord")
    Optional<Fine> findByIssueRecord(IssueRecord issueRecord);

    List<Fine> findByUserAndPaid(User user, boolean paid);

    boolean existsByUser(User user);
}