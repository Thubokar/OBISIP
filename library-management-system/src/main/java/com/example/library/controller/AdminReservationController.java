package com.example.library.controller;

import com.example.library.entity.Reservation;
import com.example.library.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminReservationController {

    private final ReservationService reservationService;

    public AdminReservationController(
            ReservationService reservationService) {

        this.reservationService = reservationService;
    }

    @GetMapping("/admin/reservations")
    public String adminReservations(Model model) {

        List<Reservation> reservations =
                reservationService.getAllReservations();

        model.addAttribute("reservations", reservations);

        return "admin-reservations";
    }

    @PostMapping("/admin/reservations/{id}/fulfill")
    public String fulfillReservation(
            @PathVariable Long id) {

        reservationService.markAsFulfilled(id);

        return "redirect:/admin/reservations";
    }
}