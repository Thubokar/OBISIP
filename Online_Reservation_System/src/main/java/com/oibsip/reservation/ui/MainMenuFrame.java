
package com.oibsip.reservation.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainMenuFrame extends JFrame {

    public MainMenuFrame() {

        setTitle(
                "OIBSIP - Online Reservation System"
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
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
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                UITheme.LIGHT_BLUE
        );

        // =====================================================
        // HEADER
        // =====================================================

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(
                UITheme.NAVY
        );

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        40,
                        20,
                        40
                )
        );

        JLabel title =
                new JLabel(
                        "🚆  ONLINE RESERVATION SYSTEM"
                );

        title.setForeground(
                UITheme.WHITE
        );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        25
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Train Ticket Reservation Portal"
                );

        subtitle.setForeground(
                new java.awt.Color(
                        215,
                        230,
                        245
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

        // =====================================================
        // CENTER
        // =====================================================

        JPanel center =
                new JPanel(
                        new BorderLayout(
                                20,
                                25
                        )
                );

        center.setOpaque(false);

        center.setBorder(
                BorderFactory.createEmptyBorder(
                        45,
                        100,
                        40,
                        100
                )
        );

        // =====================================================
        // WELCOME SECTION
        // =====================================================

        JPanel welcomePanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        welcomePanel.setOpaque(false);

        JLabel welcome =
                new JLabel(
                        "Welcome to the Reservation Portal",
                        SwingConstants.CENTER
                );

        welcome.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        32
                )
        );

        welcome.setForeground(
                UITheme.TEXT
        );

        JLabel description =
                new JLabel(
                        "Book a new ticket or manage an existing reservation.",
                        SwingConstants.CENTER
                );

        description.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        17
                )
        );

        description.setForeground(
                UITheme.MUTED
        );

        welcomePanel.add(
                welcome,
                BorderLayout.NORTH
        );

        welcomePanel.add(
                description,
                BorderLayout.CENTER
        );

        center.add(
                welcomePanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // ACTION CARDS
        // =====================================================

        JPanel cards =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                35,
                                0
                        )
                );

        cards.setOpaque(false);

        // -----------------------------------------------------
        // BOOK CARD
        // -----------------------------------------------------

        JPanel bookCard =
                createCard();

        JLabel bookIcon =
                new JLabel(
                        "🎫",
                        SwingConstants.CENTER
                );

        bookIcon.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        42
                )
        );

        JLabel bookTitle =
                new JLabel(
                        "BOOK TICKET",
                        SwingConstants.CENTER
                );

        bookTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        21
                )
        );

        bookTitle.setForeground(
                UITheme.NAVY
        );

        JLabel bookDescription =
                new JLabel(
                        "<html><center>"
                                + "Create a new train reservation<br>"
                                + "for your journey."
                                + "</center></html>",
                        SwingConstants.CENTER
                );

        bookDescription.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        bookDescription.setForeground(
                UITheme.MUTED
        );

        JButton bookButton =
                new JButton(
                        "🎫  Book Ticket"
                );

        UITheme.styleButton(
                bookButton
        );

        bookButton.setPreferredSize(
                new Dimension(
                        220,
                        48
                )
        );

        bookCard.add(
                bookIcon
        );

        bookCard.add(
                bookTitle
        );

        bookCard.add(
                bookDescription
        );

        bookCard.add(
                bookButton
        );

        // -----------------------------------------------------
        // CANCEL CARD
        // -----------------------------------------------------

        JPanel cancelCard =
                createCard();

        JLabel cancelIcon =
                new JLabel(
                        "❌",
                        SwingConstants.CENTER
                );

        cancelIcon.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        42
                )
        );

        JLabel cancelTitle =
                new JLabel(
                        "CANCEL TICKET",
                        SwingConstants.CENTER
                );

        cancelTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        21
                )
        );

        cancelTitle.setForeground(
                UITheme.NAVY
        );

        JLabel cancelDescription =
                new JLabel(
                        "<html><center>"
                                + "Find an existing reservation<br>"
                                + "and cancel your ticket."
                                + "</center></html>",
                        SwingConstants.CENTER
                );

        cancelDescription.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        cancelDescription.setForeground(
                UITheme.MUTED
        );

        JButton cancelButton =
                new JButton(
                        "❌  Cancel Ticket"
                );

        UITheme.styleDangerButton(
                cancelButton
        );

        cancelButton.setPreferredSize(
                new Dimension(
                        220,
                        48
                )
        );

        cancelCard.add(
                cancelIcon
        );

        cancelCard.add(
                cancelTitle
        );

        cancelCard.add(
                cancelDescription
        );

        cancelCard.add(
                cancelButton
        );

        cards.add(
                bookCard
        );

        cards.add(
                cancelCard
        );

        center.add(
                cards,
                BorderLayout.CENTER
        );

        mainPanel.add(
                center,
                BorderLayout.CENTER
        );

        // =====================================================
        // FOOTER
        // =====================================================

        JPanel footer =
                new JPanel(
                        new BorderLayout()
                );

        footer.setBackground(
                UITheme.WHITE
        );

        footer.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        25,
                        10,
                        25
                )
        );

        JLabel footerText =
                new JLabel(
                        "OIBSIP Reservation System"
                );

        footerText.setForeground(
                UITheme.MUTED
        );

        footerText.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        footer.add(
                footerText,
                BorderLayout.WEST
        );

        JPanel logoutPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                0,
                                0
                        )
                );

        logoutPanel.setOpaque(false);

        JButton logoutButton =
                new JButton(
                        "Logout"
                );

        UITheme.styleSecondaryButton(
                logoutButton
        );

        logoutButton.setPreferredSize(
                new Dimension(
                        110,
                        40
                )
        );

        logoutPanel.add(
                logoutButton
        );

        footer.add(
                logoutPanel,
                BorderLayout.EAST
        );

        mainPanel.add(
                footer,
                BorderLayout.SOUTH
        );

        // =====================================================
        // ACTIONS
        // =====================================================

        bookButton.addActionListener(e -> {

            dispose();

            new ReservationFrame()
                    .setVisible(true);
        });

        cancelButton.addActionListener(e -> {

            dispose();

            new CancellationFrame()
                    .setVisible(true);
        });

        logoutButton.addActionListener(e -> {

            int choice =
                    javax.swing.JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to logout?",
                            "Confirm Logout",
                            javax.swing.JOptionPane.YES_NO_OPTION,
                            javax.swing.JOptionPane.QUESTION_MESSAGE
                    );

            if (choice ==
                    javax.swing.JOptionPane.YES_OPTION) {

                dispose();

                new LoginFrame()
                        .setVisible(true);
            }
        });

        setContentPane(
                mainPanel
        );
    }

    // =====================================================
    // CREATE CARD
    // =====================================================

    private JPanel createCard() {

        JPanel card =
                new JPanel();

        card.setLayout(
                new javax.swing.BoxLayout(
                        card,
                        javax.swing.BoxLayout.Y_AXIS
                )
        );

        card.setBackground(
                UITheme.WHITE
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                UITheme.BORDER,
                                1,
                                true
                        ),
                        BorderFactory.createEmptyBorder(
                                35,
                                30,
                                35,
                                30
                        )
                )
        );

        return card;
    }
}


