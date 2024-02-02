package com.example.college.utils;

import java.util.concurrent.ThreadLocalRandom;

public class IdGenerator {

    private static final long BASE_VALUE = 100_000L;
    private static final int RANDOM_DIGITS = 6;

    public static Long generateId() {
        long timestamp = System.currentTimeMillis();
        long randomValue = ThreadLocalRandom.current().nextLong((long) Math.pow(10, RANDOM_DIGITS));
        return BASE_VALUE + timestamp + randomValue;
    }
}
