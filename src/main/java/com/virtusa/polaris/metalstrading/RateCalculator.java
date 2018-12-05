package com.virtusa.polaris.metalstrading;

import java.util.Collections;
import java.util.Map;

/**
 * Rate Configuration.
 *
 * @author Sachin Murthy
 */

public class RateCalculator {

    public static Map<String[], Float> metalRateMap;
    public static Map<String, String> romanEquivalentMetalMap;

    public static void setMetalRateMap(Map<String[], Float> rateValuesMap) {

        metalRateMap = Collections.unmodifiableMap(rateValuesMap);
    }

    public static void setRomanEquivalentMetalMap(Map<String, String> metalRomanMap) {
        romanEquivalentMetalMap = Collections.unmodifiableMap(metalRomanMap);

    }

}
