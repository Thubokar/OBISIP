package com.oibsip.reservation.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class UITheme {

    // =========================================================
    // COLORS
    // =========================================================

    public static final Color NAVY =
            new Color(15, 42, 68);

    public static final Color BLUE =
            new Color(25, 103, 180);

    public static final Color LIGHT_BLUE =
            new Color(232, 242, 252);

    public static final Color WHITE =
            Color.WHITE;

    public static final Color TEXT =
            new Color(35, 45, 55);

    public static final Color MUTED =
            new Color(100, 110, 120);

    public static final Color BORDER =
            new Color(210, 218, 226);

    public static final Color RED =
            new Color(190, 55, 55);

    public static final Color GREEN =
            new Color(40, 140, 80);

    // =========================================================
    // FONTS
    // =========================================================

    public static final Font TITLE_FONT =
            new Font(
                    "Segoe UI",
                    Font.BOLD,
                    28
            );

    public static final Font SUBTITLE_FONT =
            new Font(
                    "Segoe UI",
                    Font.PLAIN,
                    15
            );

    public static final Font LABEL_FONT =
            new Font(
                    "Segoe UI",
                    Font.BOLD,
                    14
            );

    public static final Font NORMAL_FONT =
            new Font(
                    "Segoe UI",
                    Font.PLAIN,
                    14
            );

    private UITheme() {
    }

    // =========================================================
    // PRIMARY BUTTON
    // =========================================================

    public static void styleButton(
            JButton button) {

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        button.setForeground(
                WHITE
        );

        button.setBackground(
                BLUE
        );

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setOpaque(true);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setMargin(
                new Insets(
                        10, 18, 10, 18
                )
        );
    }

    // =========================================================
    // DANGER BUTTON
    // =========================================================

    public static void styleDangerButton(
            JButton button) {

        styleButton(button);

        button.setBackground(
                RED
        );
    }

    // =========================================================
    // SECONDARY BUTTON
    // =========================================================

    public static void styleSecondaryButton(
            JButton button) {

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        button.setForeground(
                NAVY
        );

        button.setBackground(
                WHITE
        );

        button.setFocusPainted(false);

        button.setBorder(
                new LineBorder(
                        BORDER,
                        1,
                        true
                )
        );

        button.setOpaque(true);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setMargin(
                new Insets(
                        9, 18, 9, 18
                )
        );
    }

    // =========================================================
    // TEXT FIELD
    // =========================================================

    public static void styleTextField(
            JTextField field) {

        field.setFont(
                NORMAL_FONT
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                BORDER,
                                1,
                                true
                        ),
                        BorderFactory.createEmptyBorder(
                                7, 10, 7, 10
                        )
                )
        );
    }

    // =========================================================
    // COMBO BOX
    // =========================================================

    public static void styleComboBox(
            JComboBox<?> comboBox) {

        comboBox.setFont(
                NORMAL_FONT
        );

        comboBox.setBackground(
                WHITE
        );

        comboBox.setForeground(
                TEXT
        );

        comboBox.setBorder(
                new LineBorder(
                        BORDER,
                        1,
                        true
                )
        );

        comboBox.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );
    }

    // =========================================================
    // GENERIC COMPONENT
    // =========================================================

    public static void styleComponent(
            JComponent component) {

        component.setFont(
                NORMAL_FONT
        );
    }
    
    public static void stylePasswordField(
            JPasswordField field) {

        field.setFont(NORMAL_FONT);

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                BORDER,
                                1,
                                true
                        ),
                        BorderFactory.createEmptyBorder(
                                7, 10, 7, 10
                        )
                )
        );
    }
}