package com.virtusa.polaris.processor;

import com.virtusa.polaris.exception.GalaxyTradeException;
import com.virtusa.polaris.metalstrading.GalaxyMetalTrading;
import com.virtusa.polaris.metalstrading.RateCalculator;
import com.virtusa.polaris.utils.AppConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class InputProcessor {

    private static final Logger LOGGER = LogManager.getLogger(InputProcessor.class);
    private Map<String[], Float> rateValuesMap = new HashMap();
    private Map<String, String> metalRomanMap = new HashMap();


    public void processFile(String fileName) throws URISyntaxException, GalaxyTradeException {

        LOGGER.info("Processing the input...");

        try {
            Path path = Paths.get(getClass().getClassLoader()
                    .getResource(fileName).toURI());
            Stream<String> stream = Files.lines(path);

            stream.forEach(line -> {
                if (line != null && !line.isEmpty())
                    processData(line);
            });

        } catch (IOException e) {
            LOGGER.error("IO Exception occurred while reading inputs from file", e);
            throw new GalaxyTradeException("IO Exception occurred while reading inputs from file",e);
        }
    }

    /**
     * processline adds the input to various maps<K,T> based on different conditions.
     *
     * @param line
     */
    private void processData(String line) {
        String arr[] = line.split("((?<=:)|(?=:))|( )");

        if (arr.length == 3 && arr[1].equalsIgnoreCase(AppConstants.IS.getValue())) {
            metalRomanMap.put(arr[0], arr[arr.length - 1]);
            RateCalculator.setRomanEquivalentMetalMap(metalRomanMap);
        } else if (line.toLowerCase().endsWith(AppConstants.CREDITS.getValue())) {
            rateValuesMap.put(new String[]{arr[0], arr[1], arr[2]}, Float.parseFloat(arr[arr.length - 2]));
            RateCalculator.setMetalRateMap(rateValuesMap);
        } else if (line.endsWith(AppConstants.QUESTIONMARK.getValue())) {
            GalaxyMetalTrading galaxyMetal = new GalaxyMetalTrading();
            LOGGER.debug("line : " + line);
            long output = galaxyMetal.saleQuery(line);
            LOGGER.debug("Sales query processed with response :" + output);
        }
    }
}
