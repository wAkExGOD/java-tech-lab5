package com.wakexgod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        // Test Log4j2:
        logger.info("Start program...");

        Console.log("Task C");
        try {
            Numbers.execute();
        } catch(FileException e) {
            Console.log(e.getMessage());
        }

        // Test Log4j2:
        logger.info("End program.");
    }
}