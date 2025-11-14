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
 * Клас <code>EquationsApp</code> є драйвером для тестування функціональності
 * класу {@link ResultManager}, що включає обчислення та операції
 * читання/запису файлів.
 *
 * @author Andriy Ivanenko
 * @version 1.1
 */
public class EquationsApp {
    /**
     * Головний метод програми для демонстрації роботи.
     *
     * @param args Аргументи командного рядка (не використовуються).
     */
    public static void main(String[] args) {
        ResultManager manager = new ResultManager();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter X (in degrees): ");
            int x = scanner.nextInt();
            manager.calculate(x);
            System.out.println("1. Calculation result: " + manager.getResult());

            String textFile = "Result.txt";
            String binaryFile = "Result.bin";

            manager.writeResTxt(textFile);
            System.out.println("2. Result was written to " + textFile);
            manager.writeResBin(binaryFile);
            System.out.println("3. Result was written to " + binaryFile);

            manager.readResBin(binaryFile);
            System.out.println("4. Result read from binary file (" + binaryFile + "): " + manager.getResult());

            manager.readResTxt(textFile);
            System.out.println("5. Result read from text file (" + textFile + "): " + manager.getResult());

        } catch (CalcException | IOException ex) {
            System.err.println("Error: " + ex.getMessage());
        } catch (InputMismatchException ex) {
            System.err.println("Input error: Please enter an integer value for X.");
        } finally {
            scanner.close();
        }
    }
}