package com.oibsip.reservation.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.oibsip.reservation.dao.ReservationDAO;
import com.oibsip.reservation.dao.TrainDAO;
import com.oibsip.reservation.entity.Reservation;
import com.oibsip.reservation.entity.Train;
import com.oibsip.reservation.util.PnrGenerator;
import com.oibsip.reservation.util.ValidationUtil;

public class ReservationFrame extends JFrame {

    private final JTextField passengerNameField = new JTextField();
    private final JTextField trainNumberField = new JTextField();
    private final JTextField trainNameField = new JTextField();
    private final JTextField journeyDateField = new JTextField();
    private final JTextField sourceField = new JTextField();
    private final JTextField destinationField = new JTextField();

    private final JComboBox<String> classTypeBox =
            new JComboBox<>(
                    new String[]{
                            "Sleeper",
                            "AC 3 Tier",
                            "AC 2 Tier",
                            "AC First Class"
                    }
            );

    private final TrainDAO trainDAO = new TrainDAO();
    private final ReservationDAO reservationDAO = new ReservationDAO();

    private final JButton searchTrainButton =
            new JButton("🔍  Search Train");

    private final JButton bookButton =
            new JButton("🎫  Book Ticket");

    private final JButton backButton =
            new JButton("←  Back to Main Menu");

    public ReservationFrame() {

        setTitle("OIBSIP - Book Train Ticket");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setMinimumSize(new Dimension(950, 650));

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        buildUI();

        setLocationRelativeTo(null);
    }

    private void buildUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout(20, 20));

        mainPanel.setBackground(UITheme.LIGHT_BLUE);

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 60, 25, 60
                )
        );

        // ==============================
        // HEADER
        // ==============================

        JPanel header =
                new JPanel(new BorderLayout());

        header.setBackground(UITheme.NAVY);

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        18, 25, 18, 25
                )
        );

        JLabel title =
                new JLabel("🎫  BOOK TRAIN TICKET");

        title.setForeground(UITheme.WHITE);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Enter passenger and journey details"
                );

        subtitle.setForeground(
                new java.awt.Color(215, 230, 245)
        );

        subtitle.setFont(UITheme.SUBTITLE_FONT);

        header.add(title, BorderLayout.WEST);
        header.add(subtitle, BorderLayout.EAST);

        mainPanel.add(
                header,
                BorderLayout.NORTH
        );

        // ==============================
        // FORM CONTAINER
        // ==============================

        JPanel formContainer =
                new JPanel(
                        new GridLayout(1, 2, 25, 0)
                );

        formContainer.setOpaque(false);

        // ==============================
        // PASSENGER PANEL
        // ==============================

        JPanel passengerPanel =
                createPanel("Passenger Details");

        JPanel passengerForm =
                new JPanel(new GridBagLayout());

        passengerForm.setOpaque(false);

        addField(
                passengerForm,
                "Passenger Name:",
                passengerNameField,
                0
        );

        addField(
                passengerForm,
                "Class Type:",
                classTypeBox,
                1
        );

        passengerPanel.add(
                passengerForm,
                BorderLayout.CENTER
        );

        // ==============================
        // JOURNEY PANEL
        // ==============================

        JPanel journeyPanel =
                createPanel("Journey Details");

        JPanel journeyForm =
                new JPanel(new GridBagLayout());

        journeyForm.setOpaque(false);

        addField(
                journeyForm,
                "Train Number:",
                trainNumberField,
                0
        );

        GridBagConstraints searchGbc =
                new GridBagConstraints();

        searchGbc.gridx = 1;
        searchGbc.gridy = 1;
        searchGbc.weightx = 1;
        searchGbc.fill =
                GridBagConstraints.HORIZONTAL;

        searchGbc.insets =
                new Insets(5, 10, 12, 5);

        UITheme.styleButton(searchTrainButton);

        journeyForm.add(
                searchTrainButton,
                searchGbc
        );

        addField(
                journeyForm,
                "Train Name:",
                trainNameField,
                2
        );

        addField(
                journeyForm,
                "Journey Date (YYYY-MM-DD):",
                journeyDateField,
                3
        );

        addField(
                journeyForm,
                "Source Station:",
                sourceField,
                4
        );

        addField(
                journeyForm,
                "Destination Station:",
                destinationField,
                5
        );

        journeyPanel.add(
                journeyForm,
                BorderLayout.CENTER
        );

        formContainer.add(passengerPanel);
        formContainer.add(journeyPanel);

        mainPanel.add(
                formContainer,
                BorderLayout.CENTER
        );

        // ==============================
        // BOTTOM BUTTONS
        // ==============================

        JPanel bottom =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                20,
                                5
                        )
                );

        bottom.setOpaque(false);

        UITheme.styleButton(bookButton);
        UITheme.styleSecondaryButton(backButton);

        bookButton.setPreferredSize(
                new Dimension(190, 45)
        );

        backButton.setPreferredSize(
                new Dimension(190, 45)
        );

        bottom.add(bookButton);
        bottom.add(backButton);

        mainPanel.add(
                bottom,
                BorderLayout.SOUTH
        );

        setContentPane(mainPanel);

        // ==============================
        // FIELD STYLING
        // ==============================

        trainNameField.setEditable(false);

        UITheme.styleTextField(passengerNameField);
        UITheme.styleTextField(trainNumberField);
        UITheme.styleTextField(trainNameField);
        UITheme.styleTextField(journeyDateField);
        UITheme.styleTextField(sourceField);
        UITheme.styleTextField(destinationField);

        UITheme.styleComboBox(classTypeBox);

        // ==============================
        // ACTIONS
        // ==============================

        searchTrainButton.addActionListener(
                e -> findTrain()
        );

        bookButton.addActionListener(
                e -> bookTicket()
        );

        backButton.addActionListener(e -> {

            dispose();

            new MainMenuFrame().setVisible(true);
        });
    }

    // ==============================
    // CREATE FORM PANEL
    // ==============================

    private JPanel createPanel(String title) {

        JPanel panel =
                new JPanel(new BorderLayout());

        panel.setBackground(UITheme.WHITE);

        TitledBorder border =
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(
                                UITheme.BORDER,
                                1,
                                true
                        ),
                        title
                );

        border.setTitleFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );

        border.setTitleColor(UITheme.NAVY);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        border,
                        BorderFactory.createEmptyBorder(
                                15, 20, 15, 20
                        )
                )
        );

        return panel;
    }

    // ==============================
    // ADD FIELD
    // ==============================

    private void addField(
            JPanel panel,
            String labelText,
            java.awt.Component component,
            int row) {

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        5, 5, 12, 5
                );

        gbc.gridy = row;

        JLabel label =
                new JLabel(labelText);

        label.setFont(UITheme.LABEL_FONT);

        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;

        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        if (component instanceof JTextField) {

            component.setPreferredSize(
                    new Dimension(250, 40)
            );
        }

        panel.add(component, gbc);
    }

    // ==============================
    // SEARCH TRAIN
    // ==============================

    private void findTrain() {

        String trainNumberText =
                trainNumberField
                        .getText()
                        .trim();

        if (!ValidationUtil.isValidTrainNumber(
                trainNumberText)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid numeric train number.",
                    "Invalid Train Number",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            int trainNumber =
                    Integer.parseInt(trainNumberText);

            Train train =
                    trainDAO.findByTrainNumber(
                            trainNumber
                    );

            if (train == null) {

                trainNameField.setText("");

                JOptionPane.showMessageDialog(
                        this,
                        "No train was found with number "
                                + trainNumber
                                + ".",
                        "Train Not Found",
                        JOptionPane.WARNING_MESSAGE
                );

            } else {

                trainNameField.setText(
                        train.getTrainName()
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Train found successfully!\n\n"
                                + "Train Number: "
                                + trainNumber
                                + "\n"
                                + "Train Name: "
                                + train.getTrainName(),
                        "Train Found",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Train number is too large or invalid.",
                    "Invalid Train Number",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (RuntimeException e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to search for the train.\n"
                            + "Please try again.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ==============================
    // BOOK TICKET
    // ==============================

    private void bookTicket() {

        String passengerName =
                passengerNameField.getText().trim();

        String trainNumberText =
                trainNumberField.getText().trim();

        String journeyDateText =
                journeyDateField.getText().trim();

        String source =
                sourceField.getText().trim();

        String destination =
                destinationField.getText().trim();

        // ==============================
        // BASIC VALIDATION
        // ==============================

        if (passengerName.isEmpty()
                || trainNumberText.isEmpty()
                || journeyDateText.isEmpty()
                || source.isEmpty()
                || destination.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all required fields.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }
        
        if (!ValidationUtil.isValidPassengerName(passengerName)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Passenger name should contain letters and spaces only.",
                    "Invalid Passenger Name",
                    JOptionPane.WARNING_MESSAGE
            );
            passengerNameField.requestFocusInWindow();
            return;
        }

        if (!ValidationUtil.isValidTrainNumber(trainNumberText)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Train number must contain digits only.",
                    "Invalid Train Number",
                    JOptionPane.WARNING_MESSAGE
            );
            trainNumberField.requestFocusInWindow();
            return;
        }

        if (!ValidationUtil.isValidDate(journeyDateText)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the journey date in YYYY-MM-DD format.\n\n"
                            + "Example: 2026-09-01",
                    "Invalid Date",
                    JOptionPane.WARNING_MESSAGE
            );
            journeyDateField.requestFocusInWindow();
            return;
        }

        if (!ValidationUtil.isFutureOrToday(journeyDateText)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Journey date cannot be in the past.",
                    "Invalid Journey Date",
                    JOptionPane.WARNING_MESSAGE
            );
            journeyDateField.requestFocusInWindow();
            return;
        }

        if (!ValidationUtil.isValidStation(source)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid source station.",
                    "Invalid Source",
                    JOptionPane.WARNING_MESSAGE
            );
            sourceField.requestFocusInWindow();
            return;
        }

        if (!ValidationUtil.isValidStation(destination)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid destination station.",
                    "Invalid Destination",
                    JOptionPane.WARNING_MESSAGE
            );
            destinationField.requestFocusInWindow();
            return;
        }

        if (!ValidationUtil.areDifferentStations(source, destination)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Source and destination stations cannot be the same.",
                    "Invalid Journey",
                    JOptionPane.WARNING_MESSAGE
            );
            destinationField.requestFocusInWindow();
            return;
        }

        if (!ValidationUtil.isValidTrainNumber(
                trainNumberText)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Train number must contain digits only.",
                    "Invalid Train Number",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // ==============================
        // DATE VALIDATION
        // ==============================

        LocalDate journeyDate;

        try {

            journeyDate =
                    LocalDate.parse(journeyDateText);

        } catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the journey date in this format:\n\n"
                            + "YYYY-MM-DD\n\n"
                            + "Example: 2026-09-01",
                    "Invalid Date",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // ==============================
        // DATABASE OPERATIONS
        // ==============================

        try {

            int trainNumber =
                    Integer.parseInt(trainNumberText);

            Train train =
                    trainDAO.findByTrainNumber(
                            trainNumber
                    );

            if (train == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "The entered train number does not exist.",
                        "Train Not Found",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            long pnr =
                    PnrGenerator.generate();

            Reservation reservation =
                    new Reservation();

            reservation.setPnr(pnr);

            reservation.setPassengerName(
                    passengerName
            );

            reservation.setTrainNumber(
                    trainNumber
            );

            reservation.setClassType(
                    (String) classTypeBox.getSelectedItem()
            );

            reservation.setJourneyDate(
                    journeyDate
            );

            reservation.setSource(
                    source
            );

            reservation.setDestination(
                    destination
            );

            // ==============================
            // SAVE TO DATABASE
            // ==============================

            reservationDAO.save(reservation);

            // ==============================
            // SUCCESS MESSAGE
            // ==============================

            JOptionPane.showMessageDialog(
                    this,
                    "🎫  RESERVATION CONFIRMED!\n\n"
                            + "PNR: "
                            + pnr
                            + "\n"
                            + "Passenger: "
                            + passengerName
                            + "\n"
                            + "Train: "
                            + train.getTrainName()
                            + "\n"
                            + "Train Number: "
                            + trainNumber
                            + "\n"
                            + "Class: "
                            + reservation.getClassType()
                            + "\n"
                            + "Journey Date: "
                            + journeyDate
                            + "\n"
                            + "From: "
                            + source
                            + "\n"
                            + "To: "
                            + destination,
                    "Booking Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Clear form after successful booking
            clearBookingForm();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "The train number is invalid or too large.",
                    "Invalid Train Number",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "The reservation could not be completed.\n\n"
                            + "Please check your database connection "
                            + "and try again.",
                    "Booking Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ==============================
    // CLEAR BOOKING FORM
    // ==============================

    private void clearBookingForm() {

        passengerNameField.setText("");
        trainNumberField.setText("");
        trainNameField.setText("");
        journeyDateField.setText("");
        sourceField.setText("");
        destinationField.setText("");

        classTypeBox.setSelectedIndex(0);
    }
}