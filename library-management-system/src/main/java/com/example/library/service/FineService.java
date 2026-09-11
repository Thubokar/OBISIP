package com.example.library.service;

import com.example.library.entity.Fine;
import com.example.library.entity.IssueRecord;
import com.example.library.entity.User;
import com.example.library.repository.FineRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class FineService {

    private static final double FINE_PER_DAY = 5.0;

    private final FineRepository fineRepository;

    public FineService(FineRepository fineRepository) {
        this.fineRepository = fineRepository;
    }

    /**
     * Calculate the fine for an issue record.
     */
    public double calculateFine(IssueRecord issueRecord) {

        LocalDate endDate = issueRecord.isReturned()
                ? issueRecord.getReturnDate()
                : LocalDate.now();

        if (!endDate.isAfter(issueRecord.getDueDate())) {
            return 0.0;
        }

        long overdueDays = ChronoUnit.DAYS.between(
                issueRecord.getDueDate(),
                endDate
        );

        return overdueDays * FINE_PER_DAY;
    }

    /**
     * Create and save a fine if the issue record is overdue.
     * Prevents duplicate fines for the same issue.
     */
    public Fine createFineIfNeeded(IssueRecord issueRecord) {

        double amount = calculateFine(issueRecord);

        if (amount <= 0) {
            return null;
        }

        Optional<Fine> existingFine =
                fineRepository.findByIssueRecord(issueRecord);

        if (existingFine.isPresent()) {
            Fine fine = existingFine.get();

            // Update the amount in case the book is still overdue.
            fine.setAmount(amount);

            return fineRepository.save(fine);
        }

        Fine fine = new Fine(
                issueRecord,
                issueRecord.getUser(),
                amount
        );

        return fineRepository.save(fine);
    }
    
    public void updateOverdueFines(List<IssueRecord> issueRecords) {

        for (IssueRecord issueRecord : issueRecords) {

            double amount = calculateFine(issueRecord);

            if (amount <= 0) {
                continue;
            }

            Optional<Fine> existingFine =
                    fineRepository.findByIssueRecord(issueRecord);

            if (existingFine.isPresent()) {

                Fine fine = existingFine.get();
                fine.setAmount(amount);
                fineRepository.save(fine);

            } else {

                Fine fine = new Fine(
                        issueRecord,
                        issueRecord.getUser(),
                        amount
                );

                fineRepository.save(fine);
            }
        }
    }
    
    public long calculateOverdueDays(IssueRecord issueRecord) {

        LocalDate endDate = issueRecord.isReturned()
                ? issueRecord.getReturnDate()
                : LocalDate.now();

        if (!endDate.isAfter(issueRecord.getDueDate())) {
            return 0;
        }

        return ChronoUnit.DAYS.between(
                issueRecord.getDueDate(),
                endDate
        );
    }
    /**
     * Get all fines belonging to a user.
     */
    public List<Fine> getUserFines(User user) {
        return fineRepository.findByUser(user);
    }

    /**
     * Get all fines.
     */
    public List<Fine> getAllFines() {
        return fineRepository.findAll();
    }

    /**
     * Get all unpaid fines.
     */
    public List<Fine> getUnpaidFines() {
        return fineRepository.findByPaid(false);
    }

    /**
     * Mark a fine as paid.
     */
    public void markAsPaid(Long fineId) {

        Fine fine = fineRepository.findById(fineId)
                .orElseThrow(() ->
                        new RuntimeException("Fine not found"));

        fine.setPaid(true);

        fineRepository.save(fine);
    }
}