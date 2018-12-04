package com.virtusa.polaris;

import java.util.HashMap;
import java.util.Map;

public class TestData {


    public static Map<String[], Float> rateValuesMap;
    public static Map<String, String> metalRomanMap;

    public static Map<String[], Float> getMetalRateMap() {

        rateValuesMap = new HashMap();
        // Query metal words
        rateValuesMap.put(new String[]{"glob", "glob", "silver"}, 34f);
        rateValuesMap.put(new String[]{"glob", "prok", "gold"}, 57800f);
        rateValuesMap.put(new String[]{"pish", "pish", "iron"}, 3910f);
        return rateValuesMap;
    }

    public static Map<String, String> getRomanEquivalentMetalMap() {
        // setting roman equivalent of currency

        metalRomanMap = new HashMap();
        metalRomanMap.put("glob", "I");
        metalRomanMap.put("prok", "V");
        metalRomanMap.put("pish", "X");
        metalRomanMap.put("tegj", "L");
        return metalRomanMap;
    }
}
