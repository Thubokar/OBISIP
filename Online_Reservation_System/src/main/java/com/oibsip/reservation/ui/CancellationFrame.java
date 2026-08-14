package com.oibsip.reservation.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.oibsip.reservation.dao.ReservationDAO;
import com.oibsip.reservation.entity.Reservation;

public class CancellationFrame extends JFrame {

    private final JTextField pnrField = new JTextField();

    private final JTextArea detailsArea = new JTextArea();

    private final ReservationDAO reservationDAO =
            new ReservationDAO();

    private Reservation currentReservation;

    private final JButton fetchButton =
            new JButton("🔍  Fetch Booking");

    private final JButton cancelButton =
            new JButton("❌  Confirm Cancellation");

    private final JButton backButton =
            new JButton("←  Back to Main Menu");

    private final JButton logoutButton =
            new JButton("Logout");

    public CancellationFrame() {

        setTitle("OIBSIP - Cancel Reservation");

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setMinimumSize(
                new Dimension(950, 650)
        );

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        buildUI();

        setLocationRelativeTo(null);
    }

    private void buildUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout(20, 20));

        mainPanel.setBackground(
                UITheme.LIGHT_BLUE
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 60, 25, 60
                )
        );

        // =====================================
        // HEADER
        // =====================================

        JPanel header =
                new JPanel(new BorderLayout());

        header.setBackground(
                UITheme.NAVY
        );

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        18, 25, 18, 25
                )
        );

        JLabel title =
                new JLabel(
                        "❌  CANCEL RESERVATION"
                );

        title.setForeground(
                UITheme.WHITE
        );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Search your booking and confirm cancellation"
                );

        subtitle.setForeground(
                new java.awt.Color(
                        215, 230, 245
                )
        );

        subtitle.setFont(
                UITheme.SUBTITLE_FONT
        );

        header.add(
                title,
                BorderLayout.WEST
        );

        header.add(
                subtitle,
                BorderLayout.EAST
        );

        mainPanel.add(
                header,
                BorderLayout.NORTH
        );

        // =====================================
        // CENTER
        // =====================================

        JPanel center =
                new JPanel(
                        new BorderLayout(15, 20)
                );

        center.setOpaque(false);

        // =====================================
        // PNR SEARCH
        // =====================================

        JPanel searchPanel =
                new JPanel(
                        new GridBagLayout()
                );

        searchPanel.setBackground(
                UITheme.WHITE
        );

        searchPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                UITheme.BORDER,
                                1,
                                true
                        ),
                        BorderFactory.createEmptyBorder(
                                15, 20, 15, 20
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        5, 5, 5, 10
                );

        gbc.gridy = 0;

        JLabel pnrLabel =
                new JLabel("PNR Number:");

        pnrLabel.setFont(
                UITheme.LABEL_FONT
        );

        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.fill =
                GridBagConstraints.NONE;

        searchPanel.add(
                pnrLabel,
                gbc
        );

        UITheme.styleTextField(
                pnrField
        );

        pnrField.setPreferredSize(
                new Dimension(300, 42)
        );

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        searchPanel.add(
                pnrField,
                gbc
        );

        UITheme.styleButton(
                fetchButton
        );

        fetchButton.setPreferredSize(
                new Dimension(180, 42)
        );

        gbc.gridx = 2;
        gbc.weightx = 0;

        searchPanel.add(
                fetchButton,
                gbc
        );

        center.add(
                searchPanel,
                BorderLayout.NORTH
        );

        // =====================================
        // BOOKING DETAILS
        // =====================================

        JPanel detailsPanel =
                new JPanel(
                        new BorderLayout()
                );

        detailsPanel.setBackground(
                UITheme.WHITE
        );

        TitledBorder detailsBorder =
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(
                                UITheme.BORDER,
                                1,
                                true
                        ),
                        "Booking Details"
                );

        detailsBorder.setTitleFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );

        detailsBorder.setTitleColor(
                UITheme.NAVY
        );

        detailsPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        detailsBorder,
                        BorderFactory.createEmptyBorder(
                                15, 20, 15, 20
                        )
                )
        );

        detailsArea.setEditable(false);

        detailsArea.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        detailsArea.setForeground(
                UITheme.TEXT
        );

        detailsArea.setBackground(
                UITheme.WHITE
        );

        detailsArea.setLineWrap(false);

        detailsArea.setBorder(null);

        detailsPanel.add(
                detailsArea,
                BorderLayout.CENTER
        );

        center.add(
                detailsPanel,
                BorderLayout.CENTER
        );

        // =====================================
        // CONFIRM BUTTON
        // =====================================

        JPanel actionPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                20,
                                5
                        )
                );

        actionPanel.setOpaque(false);

        UITheme.styleDangerButton(
                cancelButton
        );

        cancelButton.setPreferredSize(
                new Dimension(230, 48)
        );

        actionPanel.add(
                cancelButton
        );

        center.add(
                actionPanel,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                center,
                BorderLayout.CENTER
        );

        // =====================================
        // FOOTER
        // =====================================

        JPanel footer =
                new JPanel(
                        new BorderLayout()
                );

        footer.setBackground(
                UITheme.WHITE
        );

        footer.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 15, 10, 15
                )
        );

        JPanel leftButtons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                0
                        )
                );

        leftButtons.setOpaque(false);

        UITheme.styleSecondaryButton(
                backButton
        );

        backButton.setPreferredSize(
                new Dimension(200, 42)
        );

        leftButtons.add(
                backButton
        );

        JPanel rightButtons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                0
                        )
                );

        rightButtons.setOpaque(false);

        UITheme.styleSecondaryButton(
                logoutButton
        );

        logoutButton.setPreferredSize(
                new Dimension(110, 42)
        );

        rightButtons.add(
                logoutButton
        );

        footer.add(
                leftButtons,
                BorderLayout.WEST
        );

        footer.add(
                rightButtons,
                BorderLayout.EAST
        );

        mainPanel.add(
                footer,
                BorderLayout.SOUTH
        );

        setContentPane(mainPanel);

        // =====================================
        // ACTIONS
        // =====================================

        fetchButton.addActionListener(
                e -> fetchReservation()
        );

        cancelButton.addActionListener(
                e -> cancelReservation()
        );

        backButton.addActionListener(e -> {

            dispose();

            new MainMenuFrame()
                    .setVisible(true);
        });

        logoutButton.addActionListener(e -> {

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to logout?",
                            "Confirm Logout",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );

            if (choice == JOptionPane.YES_OPTION) {

                dispose();

                new LoginFrame()
                        .setVisible(true);
            }
        });
    }

    // =====================================
    // FETCH RESERVATION
    // =====================================

    private void fetchReservation() {

        String pnrText =
                pnrField.getText().trim();

        if (!pnrText.matches("\\d+")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid numeric PNR.",
                    "Invalid PNR",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            long pnr =
                    Long.parseLong(pnrText);

            currentReservation =
                    reservationDAO.findByPnr(pnr);

            if (currentReservation == null) {

                detailsArea.setText("");

                JOptionPane.showMessageDialog(
                        this,
                        "No reservation found for PNR "
                                + pnr
                                + ".",
                        "Reservation Not Found",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            detailsArea.setText(
                    "PNR             : "
                    + currentReservation.getPnr()
                    + "\n\n"
                    + "Passenger       : "
                    + currentReservation.getPassengerName()
                    + "\n\n"
                    + "Train Number    : "
                    + currentReservation.getTrainNumber()
                    + "\n\n"
                    + "Class           : "
                    + currentReservation.getClassType()
                    + "\n\n"
                    + "Journey Date    : "
                    + currentReservation.getJourneyDate()
                    + "\n\n"
                    + "From            : "
                    + currentReservation.getSource()
                    + "\n\n"
                    + "To              : "
                    + currentReservation.getDestination()
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "The PNR number is too large or invalid.",
                    "Invalid PNR",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (RuntimeException e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to fetch the reservation.\n\n"
                            + "Please check your database connection "
                            + "and try again.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================
    // CANCEL RESERVATION
    // =====================================

    private void cancelReservation() {

        if (currentReservation == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fetch a reservation first.",
                    "No Reservation Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to cancel this reservation?\n\n"
                                + "PNR: "
                                + currentReservation.getPnr()
                                + "\nPassenger: "
                                + currentReservation.getPassengerName(),
                        "Confirm Cancellation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        try {

            reservationDAO.delete(
                    currentReservation
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Reservation cancelled successfully!\n\n"
                            + "PNR: "
                            + currentReservation.getPnr(),
                    "Cancellation Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            currentReservation = null;

            pnrField.setText("");

            detailsArea.setText("");

        } catch (RuntimeException e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "The reservation could not be cancelled.\n\n"
                            + "Please check your database connection "
                            + "and try again.",
                    "Cancellation Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}