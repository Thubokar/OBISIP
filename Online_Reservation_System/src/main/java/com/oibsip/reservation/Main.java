package com.oibsip.reservation;

import javax.swing.SwingUtilities;

import com.oibsip.reservation.ui.LoginFrame;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new LoginFrame().setVisible(true);

        });
    }
}