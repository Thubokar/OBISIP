package com.example.library.repository;

import com.example.library.entity.Book;
import com.example.library.entity.Reservation;
import com.example.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository
        extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUser(User user);

    List<Reservation> findByBook(Book book);

    List<Reservation> findByFulfilled(boolean fulfilled);

    boolean existsByUserAndBookAndFulfilledFalse(
            User user,
            Book book
    );

    Optional<Reservation> findFirstByBookAndFulfilledFalseOrderByReservationDateAsc(
            Book book
    );

    boolean existsByUser(User user);
}