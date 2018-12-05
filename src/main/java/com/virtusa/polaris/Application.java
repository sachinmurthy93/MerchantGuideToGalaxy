package com.virtusa.polaris;

import com.virtusa.polaris.processor.InputProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private InputProcessor inputProcessor;

    private static final Logger LOGGER = LogManager.getLogger(Application.class);

    public static void main(String[] args)  {
        SpringApplication.run(Application.class, args);
    }

    //access command line arguments
    @Override
    public void run(String... args) {
        LOGGER.info("Application merchant guide to galaxy started ...");
        String fileName = "input.txt";
        if (args.length != 0)
            fileName = args[0];
        try {
            inputProcessor.processFile(fileName);
        } catch (Exception e) {
            LOGGER.error("Exception occurred in the main application ");
        }
    }
}
