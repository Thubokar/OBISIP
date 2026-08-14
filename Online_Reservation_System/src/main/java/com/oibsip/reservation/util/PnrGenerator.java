package com.oibsip.reservation.util;

import java.util.concurrent.ThreadLocalRandom;

public class PnrGenerator {

    private static final long MIN_PNR = 1_000_000_000L;
    private static final long MAX_PNR = 9_999_999_999L;

    private PnrGenerator() {
        // Prevent object creation
    }

    public static long generate() {

        return ThreadLocalRandom.current()
                .nextLong(
                        MIN_PNR,
                        MAX_PNR + 1
                );
    }
}