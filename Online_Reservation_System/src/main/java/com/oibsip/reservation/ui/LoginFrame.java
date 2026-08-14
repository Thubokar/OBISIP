
package com.oibsip.reservation.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.oibsip.reservation.dao.UserDAO;
import com.oibsip.reservation.entity.User;

public class LoginFrame extends JFrame {

    private final JTextField usernameField =
            new JTextField();

    private final JPasswordField passwordField =
            new JPasswordField();

    private final JButton loginButton =
            new JButton("🔐  Login");

    private final UserDAO userDAO =
            new UserDAO();

    public LoginFrame() {

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
                        30,
                        40,
                        30,
                        40
                )
        );

        JLabel icon =
                new JLabel(
                        "🚆",
                        SwingConstants.CENTER
                );

        icon.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        44
                )
        );

        JLabel title =
                new JLabel(
                        "ONLINE RESERVATION SYSTEM",
                        SwingConstants.CENTER
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
                        "Train Ticket Reservation Portal",
                        SwingConstants.CENTER
                );

        subtitle.setForeground(
                new java.awt.Color(
                        210,
                        225,
                        240
                )
        );

        subtitle.setFont(
                UITheme.SUBTITLE_FONT
        );

        JPanel titlePanel =
                new JPanel(
                        new BorderLayout(
                                5,
                                5
                        )
                );

        titlePanel.setOpaque(false);

        titlePanel.add(
                title,
                BorderLayout.CENTER
        );

        titlePanel.add(
                subtitle,
                BorderLayout.SOUTH
        );

        header.add(
                icon,
                BorderLayout.NORTH
        );

        header.add(
                titlePanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                header,
                BorderLayout.NORTH
        );

        // =====================================================
        // CENTER AREA
        // =====================================================

        JPanel center =
                new JPanel(
                        new GridBagLayout()
                );

        center.setOpaque(false);

        center.setBorder(
                BorderFactory.createEmptyBorder(
                        35,
                        40,
                        35,
                        40
                )
        );

        // =====================================================
        // LOGIN CARD
        // =====================================================

        JPanel card =
                new JPanel(
                        new GridBagLayout()
                );

        card.setBackground(
                UITheme.WHITE
        );

        card.setPreferredSize(
                new Dimension(
                        650,
                        430
                )
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
                                55,
                                35,
                                55
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;

        gbc.insets =
                new Insets(
                        8,
                        5,
                        8,
                        5
                );

        // =====================================================
        // LOGIN TITLE
        // =====================================================

        JLabel loginTitle =
                new JLabel(
                        "Welcome Back",
                        SwingConstants.CENTER
                );

        loginTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        loginTitle.setForeground(
                UITheme.TEXT
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        gbc.insets =
                new Insets(
                        5,
                        5,
                        8,
                        5
                );

        card.add(
                loginTitle,
                gbc
        );

        // =====================================================
        // DESCRIPTION
        // =====================================================

        JLabel loginDescription =
                new JLabel(
                        "Login to manage your train reservations.",
                        SwingConstants.CENTER
                );

        loginDescription.setFont(
                UITheme.SUBTITLE_FONT
        );

        loginDescription.setForeground(
                UITheme.MUTED
        );

        gbc.gridy = 1;

        gbc.insets =
                new Insets(
                        0,
                        5,
                        25,
                        5
                );

        card.add(
                loginDescription,
                gbc
        );

        // =====================================================
        // USERNAME LABEL
        // =====================================================

        JLabel usernameLabel =
                new JLabel(
                        "Username"
                );

        usernameLabel.setFont(
                UITheme.LABEL_FONT
        );

        usernameLabel.setForeground(
                UITheme.TEXT
        );

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;

        gbc.weightx = 0;

        gbc.fill =
                GridBagConstraints.NONE;

        gbc.anchor =
                GridBagConstraints.WEST;

        gbc.insets =
                new Insets(
                        8,
                        5,
                        5,
                        15
                );

        card.add(
                usernameLabel,
                gbc
        );

        // =====================================================
        // USERNAME FIELD
        // =====================================================

        UITheme.styleTextField(
                usernameField
        );

        usernameField.setPreferredSize(
                new Dimension(
                        380,
                        45
                )
        );

        gbc.gridx = 1;
        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.anchor =
                GridBagConstraints.CENTER;

        gbc.insets =
                new Insets(
                        8,
                        5,
                        5,
                        5
                );

        card.add(
                usernameField,
                gbc
        );

        // =====================================================
        // PASSWORD LABEL
        // =====================================================

        JLabel passwordLabel =
                new JLabel(
                        "Password"
                );

        passwordLabel.setFont(
                UITheme.LABEL_FONT
        );

        passwordLabel.setForeground(
                UITheme.TEXT
        );

        gbc.gridx = 0;
        gbc.gridy = 3;

        gbc.weightx = 0;

        gbc.fill =
                GridBagConstraints.NONE;

        gbc.anchor =
                GridBagConstraints.WEST;

        gbc.insets =
                new Insets(
                        12,
                        5,
                        5,
                        15
                );

        card.add(
                passwordLabel,
                gbc
        );

        // =====================================================
        // PASSWORD FIELD
        // =====================================================

        UITheme.stylePasswordField(
                passwordField
        );

        passwordField.setPreferredSize(
                new Dimension(
                        380,
                        45
                )
        );

        gbc.gridx = 1;
        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.anchor =
                GridBagConstraints.CENTER;

        gbc.insets =
                new Insets(
                        12,
                        5,
                        5,
                        5
                );

        card.add(
                passwordField,
                gbc
        );

        // =====================================================
        // LOGIN BUTTON
        // =====================================================

        UITheme.styleButton(
                loginButton
        );

        loginButton.setPreferredSize(
                new Dimension(
                        380,
                        50
                )
        );

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;

        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.insets =
                new Insets(
                        28,
                        5,
                        8,
                        5
                );

        card.add(
                loginButton,
                gbc
        );

        // =====================================================
        // SECURITY MESSAGE
        // =====================================================

        JLabel securityText =
                new JLabel(
                        "🔒  Secure reservation management",
                        SwingConstants.CENTER
                );

        securityText.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        securityText.setForeground(
                UITheme.MUTED
        );

        gbc.gridy = 5;

        gbc.insets =
                new Insets(
                        10,
                        5,
                        5,
                        5
                );

        card.add(
                securityText,
                gbc
        );

        // =====================================================
        // ADD CARD TO CENTER
        // =====================================================

        GridBagConstraints centerGbc =
                new GridBagConstraints();

        centerGbc.gridx = 0;
        centerGbc.gridy = 0;

        centerGbc.weightx = 1;
        centerGbc.weighty = 1;

        centerGbc.anchor =
                GridBagConstraints.CENTER;

        center.add(
                card,
                centerGbc
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
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(
                                1,
                                0,
                                0,
                                0,
                                UITheme.BORDER
                        ),
                        BorderFactory.createEmptyBorder(
                                10,
                                25,
                                10,
                                25
                        )
                )
        );

        JLabel footerLabel =
                new JLabel(
                        "OIBSIP Online Reservation System"
                );

        footerLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        footerLabel.setForeground(
                UITheme.MUTED
        );

        footer.add(
                footerLabel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                footer,
                BorderLayout.SOUTH
        );

        setContentPane(
                mainPanel
        );

        // =====================================================
        // ACTIONS
        // =====================================================

        loginButton.addActionListener(
                e -> login()
        );

        passwordField.addActionListener(
                e -> login()
        );

        usernameField.addActionListener(
                e -> passwordField.requestFocusInWindow()
        );
    }

    // =========================================================
    // LOGIN
    // =========================================================

    private void login() {

        String username =
                usernameField
                        .getText()
                        .trim();

        String password =
                new String(
                        passwordField
                                .getPassword()
                );

        // =====================================================
        // VALIDATION
        // =====================================================

        if (username.isEmpty()
                && password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter your username and password.",
                    "Login Required",
                    JOptionPane.WARNING_MESSAGE
            );

            usernameField.requestFocusInWindow();

            return;
        }

        if (username.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter your username.",
                    "Username Required",
                    JOptionPane.WARNING_MESSAGE
            );

            usernameField.requestFocusInWindow();

            return;
        }

        if (password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter your password.",
                    "Password Required",
                    JOptionPane.WARNING_MESSAGE
            );

            passwordField.requestFocusInWindow();

            return;
        }

        // =====================================================
        // AUTHENTICATION
        // =====================================================

        try {

            User user =
                    userDAO.authenticate(
                            username,
                            password
                    );

            if (user != null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Login successful!\n\n"
                                + "Welcome, "
                                + username
                                + "!",
                        "Login Successful",
                        JOptionPane.INFORMATION_MESSAGE
                );

                dispose();

                new MainMenuFrame()
                        .setVisible(true);

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid username or password.\n\n"
                                + "Please check your credentials and try again.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );

                passwordField.setText("");

                passwordField.requestFocusInWindow();
            }

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to connect to the login service.\n\n"
                            + "Please check your database connection "
                            + "and try again.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}

