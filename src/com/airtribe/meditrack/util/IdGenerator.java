package com.airtribe.meditrack.util;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {

    private static final AtomicLong doctorCounter = new AtomicLong(1);
    private static final AtomicLong patientCounter = new AtomicLong(1);
    private static final AtomicLong appointmentCounter = new AtomicLong(1);
    private static final AtomicLong billCounter = new AtomicLong(1);

    // Generate a sequential numeric ID based on prefix
    private static long generateSequentialId(String prefix) {
        return switch (prefix) {
            case "DOC" -> doctorCounter.getAndIncrement();
            case "PAT" -> patientCounter.getAndIncrement();
            case "APT" -> appointmentCounter.getAndIncrement();
            case "BILL" -> billCounter.getAndIncrement();
            default -> throw new IllegalArgumentException("Unknown prefix: " + prefix);
        };
    }

    // Generate a custom ID with prefix
    public static String generateId(String prefix) {
        return prefix + "-" + generateSequentialId(prefix);
    }
}