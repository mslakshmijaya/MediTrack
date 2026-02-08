package com.airtribe.meditrack.util;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {

    private static final AtomicLong doctorCounter = new AtomicLong(1);
    private static final AtomicLong patientCounter = new AtomicLong(1);

    // Generate a sequential numeric ID
    private static long generateSequentialId(boolean isDocId) {
        return isDocId ? doctorCounter.getAndIncrement() : patientCounter.getAndIncrement();
    }

    // Generate a custom ID with prefix
    public static String generateId(String prefix) {
        return prefix + "-" + generateSequentialId(prefix.equals("DOC"));
    }



}
