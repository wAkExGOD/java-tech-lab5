package com.wakexgod;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IllformedLocaleException;
import java.util.List;
import java.util.Locale;

public class FloatNumberProcessor {
    private List<Float> numbers = new ArrayList<>();

    public void readNumbersFromFile(String filePath) throws CustomException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                processLine(line);
            }
        } catch (IOException e) {
            throw new CustomException("Error reading file: " + e.getMessage());
        }
    }

    private void processLine(String line) throws CustomException {
        String[] parts = line.split(";");
        if (parts.length != 2) {
            throw new CustomException("Invalid line format: " + line);
        }

        String numberStr = parts[0].trim();
        String localeStr = parts[1].trim();

        // Проверяем, является ли строка корректным числом
        if (!numberStr.matches("-?\\d+(\\.\\d+)?")) {
            throw new CustomException("Invalid number format: " + numberStr); // Если формат неверный
        }

        // Проверка корректности локали
        if (!isValidLocale(localeStr)) {
            throw new CustomException("Invalid locale: " + localeStr);
        }
        Locale locale = Locale.forLanguageTag(localeStr);

        try {
            // Преобразуем строку в число вручную
            float value = Float.parseFloat(numberStr);
            Console.log("num " + value);

            // Проверка на допустимые значения, учитывающая отрицательные числа
            if (value < Float.NEGATIVE_INFINITY || value > Float.MAX_VALUE) {
                throw new CustomException("Number out of bounds: " + value);
            }
            numbers.add(value); // Добавление числа в список
        } catch (NumberFormatException e) {
            throw new CustomException("Invalid number: " + numberStr); // Обработка некорректного числа
        }
    }

    private boolean isValidLocale(String localeStr) {
        try {
            Locale locale = Locale.forLanguageTag(localeStr);
            return !locale.getLanguage().isEmpty(); // Проверяем, что язык не пустой
        } catch (IllformedLocaleException e) {
            return false; // Неверный формат локали
        }
    }

    public float getSum() {
        return (float) numbers.stream().mapToDouble(Float::doubleValue).sum();
    }

    public float getAverage() {
        return numbers.isEmpty() ? 0 : getSum() / numbers.size();
    }

    // Метод для записи чисел в файл
    public void writeResultsToFile(String filePath) throws CustomException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Sum: " + getSum());
            writer.newLine(); // Перевод строки
            writer.write("Average: " + getAverage());
            writer.newLine(); // Перевод строки

            // Запись каждого числа
            writer.write("Numbers: ");
            for (Float number : numbers) {
                writer.write(number.toString() + " ");
            }
            writer.newLine(); // Перевод строки
        } catch (IOException e) {
            throw new CustomException("Error writing to file: " + e.getMessage());
        }
    }
}