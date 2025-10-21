/****************************************************************************
 * Copyright (c) 2023-2025 Lviv Polytechnic National University. All Rights Reserved.
 *
 * This program and the accompanying materials are made available under the terms
 * of the Academic Free License v. 3.0 which accompanies this distribution, and is
 * available at https://opensource.org/license/afl-3-0-php/
 *
 * SPDX-License-Identifier: AFL-3.0
 ****************************************************************************/

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Клас <code>Lab5Test</code> є драйвером для тестування функціональності
 * класу {@link ResultManager}, включаючи обчислення та операції
 * читання/запису файлів.
 *
 * @author Andriy Ivanenko
 * @version 1.0
 */
public class Lab5Test {
    /**
     * Головний метод програми для тестування.
     *
     * @param args Аргументи командного рядка (не використовуються).
     */
    public static void main(String[] args) {
        ResultManager manager = new ResultManager();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter X (in degrees): ");
            int x = scanner.nextInt();

            // 1. Обчислення
            manager.calculate(x);
            System.out.println("Calculation result: " + manager.getResult());

            String textFile = "Result.txt";
            String binaryFile = "Result.bin";

            // 2. Запис у файли
            manager.writeResTxt(textFile);
            System.out.println("Result was written to " + textFile);
            manager.writeResBin(binaryFile);
            System.out.println("Result was written to " + binaryFile);

            // 3. Читання з двійкового файлу та перевірка
            manager.readResBin(binaryFile);
            System.out.println("Result read from " + binaryFile + ": " + manager.getResult());

            // 4. Читання з текстового файлу та перевірка
            manager.readResTxt(textFile);
            System.out.println("Result read from " + textFile + ": " + manager.getResult());

        } catch (CalcException | IOException | InputMismatchException ex) {
            System.err.println("An error occurred: " + ex.getMessage());
        } finally {
            scanner.close();
        }
    }
}