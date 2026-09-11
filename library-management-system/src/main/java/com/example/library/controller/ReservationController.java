package com.example.library.controller;

import com.example.library.entity.Reservation;
import com.example.library.service.ReservationService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(
            ReservationService reservationService) {

        this.reservationService = reservationService;
    }

    @GetMapping("/user/reservations")
    public String myReservations(
            Authentication authentication,
            Model model) {

       

        List<Reservation> reservations =
                reservationService.getUserReservations(
                        authentication.getName()
                );

        

        model.addAttribute("reservations", reservations);

        return "reservations";
    }
}