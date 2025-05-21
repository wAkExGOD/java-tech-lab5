package com.wakexgod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        // Test Log4j2:
        logger.info("Start program...");

        Console.log("Task C");
        FloatNumberProcessor processor = new FloatNumberProcessor();
        try {
            processor.readNumbersFromFile("src/main/resources/numbers.txt");
            Console.log("Sum: " + processor.getSum());
            Console.log("Average: " + processor.getAverage());

            // Запись результатов в файл
            processor.writeResultsToFile("src/main/resources/output.txt");
            Console.log("Results written to output.txt");
        } catch (CustomException e) {
            Console.log(e.getMessage());
        }

        // Test Log4j2:
        logger.info("End program.");
    }
}