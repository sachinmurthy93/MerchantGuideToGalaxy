package com.virtusa.polaris.metalstrading;

import java.util.stream.IntStream;

/**
 * This class provides implementation for Galaxy Number System Translation.
 */
public class GalaxyNumberSystemTranslation {

    /**
     * Number conversion class.
     *
     * @param romanNumber
     * @return int
     */

    public static int convertRomanNumberToInteger(final String romanNumber) {
        final int[] intStore = new int[2];
        final int length = romanNumber.length();

        IntStream.range(0, length - 1).forEach(charIndex -> {
            // character value

            if (isCurrentGreaterThanNext(romanNumber, charIndex)) {
                intStore[0] = intStore[0] + getRomanNumberToIntEquivalent(romanNumber.charAt(charIndex)) + intStore[1];
                intStore[1] = 0;

            } else if (isCurrentLessThanNext(romanNumber, charIndex)) {
                intStore[1] = -intStore[1] - getRomanNumberToIntEquivalent(romanNumber.charAt(charIndex));

            } else {
                // if both are equal
                intStore[1] = intStore[1] + getRomanNumberToIntEquivalent(romanNumber.charAt(charIndex));

            }
        });


        return intStore[0] + intStore[1] + getRomanNumberToIntEquivalent(romanNumber.charAt(length - 1));
    }

    private static boolean isCurrentGreaterThanNext(String romanNumber, int charIndex) {
        return getRomanNumberToIntEquivalent(
                romanNumber.charAt(charIndex)) >
                getRomanNumberToIntEquivalent(romanNumber.charAt(charIndex + 1));
    }

    private static boolean isCurrentLessThanNext(String romanNumber, int charIndex) {
        return getRomanNumberToIntEquivalent(
                romanNumber.charAt(charIndex)) <
                getRomanNumberToIntEquivalent(romanNumber.charAt(charIndex + 1));
    }

    // Get integer value for corresponding Roman character
    private static int getRomanNumberToIntEquivalent(final char romanCharacter) {
        return RomanNumberToIntMap.romanNumberToIntMap.get(Character.toString(romanCharacter));
    }

}
