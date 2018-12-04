package utils;

/**
 * Enum with application constants.
 */

public enum AppConstants {

    METAL("metal"),
    METALQUANTITY("metalQuantity"),
    SPACE(" ");

    private String value;

    AppConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
