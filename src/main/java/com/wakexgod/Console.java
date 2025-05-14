package com.wakexgod;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Console {
    // Разработчик
    private static final String DEVELOPER = "Vladislav";

    // Метод для логирования
    public static void log(String message) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
        String date = dateFormatter.format(new Date());
        String time = timeFormatter.format(new Date());
        System.out.println(DEVELOPER + " [" + date + " " + time + "]: " + message);
    }
}
