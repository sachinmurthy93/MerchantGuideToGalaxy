package com.virtusa.polaris.utils;

/**
 * Enum with application constants.
 */

public enum AppConstants {

    METAL("metal"),
    METALQUANTITY("metalQuantity"),
    SPACE(" "),
    CREDITS("credits"),
    IS("is"),
    QUESTIONMARK("?");

    private String value;

    AppConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
