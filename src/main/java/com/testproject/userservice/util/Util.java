package com.testproject.userservice.util;

import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class Util {

    public String generateCardNumber() {
        Random random = new Random();
        StringBuilder number = new StringBuilder();

        // Добавляем начальные четыре цифры
        number.append("4817");

        // Генерируем оставшиеся 12 цифр
        for (int i = 0; i < 12; i++) {
            int digit = random.nextInt(10); // Генерируем случайную цифру от 0 до 9
            number.append(digit);
        }

        return number.toString();
    }
//    public String generateAccountNumber() {
//        Random random = new Random();
//        StringBuilder number = new StringBuilder();
//
//        // Добавляем начальные четыре цифры
//        number.append("40817810");
//
//        // Генерируем оставшиеся 12 цифр
//        for (int i = 0; i < 12; i++) {
//            int digit = random.nextInt(10); // Генерируем случайную цифру от 0 до 9
//            number.append(digit);
//        }
//
//        return number.toString();
//    }
}
