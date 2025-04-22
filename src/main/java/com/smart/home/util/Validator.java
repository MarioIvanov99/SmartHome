package com.smart.home.util;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Validator {
    public static void checkNotNegative(BigDecimal value, String fieldName) {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be negative.");
        }
    }

    public static void checkNotNegative(int value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be negative.");
        }
    }

    public static void checkProductionDate(LocalDate date) {
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Production date cannot be in the future.");
        }
    }
}
