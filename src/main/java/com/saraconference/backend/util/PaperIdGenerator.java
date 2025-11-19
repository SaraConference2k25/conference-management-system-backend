package com.saraconference.backend.util;

import java.util.Random;

public class PaperIdGenerator {
    private static final String PREFIX = "NCE";
    private static final String ALPHANUMERIC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random random = new Random();

    public static String generatePaperId() {
        StringBuilder id = new StringBuilder(PREFIX);
        for (int i = 0; i < 4; i++) {
            id.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
        }
        return id.toString();
    }
}
