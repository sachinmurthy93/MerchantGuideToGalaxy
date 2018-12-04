package com.virtusa.polaris.metalstrading;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RomanNumberToIntMap {

    public static Map<String, Integer> romanNumberToIntMap;

    static {

        // setting roman equivalent currency
        Map<String, Integer> romanNumberIntMap = new HashMap();
        romanNumberIntMap.put("I", 1);
        romanNumberIntMap.put("V", 5);
        romanNumberIntMap.put("X", 10);
        romanNumberIntMap.put("L", 50);
        romanNumberIntMap.put("C", 100);
        romanNumberIntMap.put("D", 500);
        romanNumberIntMap.put("M", 1000);

        romanNumberToIntMap = Collections.unmodifiableMap(romanNumberIntMap);
    }
}
