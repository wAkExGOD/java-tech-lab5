package com.wakexgod;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Numbers {
    public static void execute() throws FileException {
        ArrayList<Double> numbers = new ArrayList<>();

        try{
            File file = new File("D:\\MMF\\java\\technologies\\Lab5\\numbers.txt");
            if (!file.exists()) {
                Console.log("Файл не найден");
                throw new FileException("Файл не найден");
            }

            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String numStr = scanner.next();

                // Если локаль валидна
                String localeStr = scanner.next();
                if (!localeStr.matches("[a-zA-Z-]+")) {
                    Console.log("Локаль " + localeStr + " введена некорректно!");
                    throw new FileException("Локаль " + localeStr + " введена некорректно!");
                }

                NumberFormat numberFormat = NumberFormat.getInstance(Locale.forLanguageTag(localeStr));

                // Преобразуем строку в число
                try {
                    Console.log(numberFormat.toString());
                    Number number = numberFormat.parse(numStr);
                    Console.log(numStr + " " + Locale.forLanguageTag(localeStr) + " " + number + " " + number.doubleValue());
                    numbers.add(number.doubleValue());
                } catch (Exception e) {
                    Console.log("Одно из чисел имеет некорректный формат! (Число " + numStr + ")");
                    throw new FileException("Одно из чисел имеет некорректный формат! (Число " + numStr + ")");
                }
            }

            double sum = 0;

            Console.log("Числа в файле:");
            for (int i = 0; i < numbers.size(); i++) {
                double number = numbers.get(i);
                sum += number;
                Console.log((i + 1) + "-ое число: " + number);
            }

            Console.log("Сумма: " + sum);
            Console.log("Среднее арифмитическое: " + (sum / numbers.size()));
        } catch (FileException e){
            e.printStackTrace();
        } catch (OutOfMemoryError e) {
            Console.log("Недостаточно памяти!");
            throw new OutOfMemoryError("Недостаточно памяти!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            Console.log("У последнего числа не указана локаль!");
            throw new NoSuchElementException("У последнего числа не указана локаль!");
        } catch (NumberFormatException e) {
            Console.log("Число слишком большое!");
            throw new NumberFormatException("Число слишком большое!");
        }
    }
}
