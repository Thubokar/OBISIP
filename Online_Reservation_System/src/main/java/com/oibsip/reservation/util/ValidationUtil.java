package com.oibsip.reservation.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static boolean isValidPassengerName(String name) {

        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        return name.trim().matches("[a-zA-Z ]{2,100}");
    }

    public static boolean isValidTrainNumber(String trainNumber) {

        if (trainNumber == null || trainNumber.trim().isEmpty()) {
            return false;
        }

        return trainNumber.trim().matches("\\d+");
    }

    public static boolean isValidDate(String dateText) {

        if (dateText == null || dateText.trim().isEmpty()) {
            return false;
        }

        try {
            LocalDate.parse(dateText.trim());
            return true;

        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isFutureOrToday(String dateText) {

        try {
            LocalDate date = LocalDate.parse(dateText.trim());

            return !date.isBefore(LocalDate.now());

        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isValidStation(String station) {

        if (station == null || station.trim().isEmpty()) {
            return false;
        }

        return station.trim().matches("[a-zA-Z ]{2,100}");
    }

    public static boolean areDifferentStations(
            String source,
            String destination) {

        if (source == null || destination == null) {
            return false;
        }

        return !source.trim().equalsIgnoreCase(
                destination.trim()
        );
    }
}