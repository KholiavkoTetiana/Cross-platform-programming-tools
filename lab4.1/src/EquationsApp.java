/****************************************************************************
 * Copyright (c) 2023-2025 Lviv Polytechnic National University. All Rights Reserved.
 *
 * This program and the accompanying materials are made available under the terms
 * of the Academic Free License v. 3.0 which accompanies this distribution, and is
 * available at https://opensource.org/license/afl-3-0-php/
 *
 * SPDX-License-Identifier: AFL-3.0
 ****************************************************************************/


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import static java.lang.System.out;

/**
 * Клас <code>EquationsApp</code> є драйвером для класу {@link Equations}.
 * Він керує введенням даних, викликом обчислень та збереженням результату.
 *
 * @author Andriy Ivanenko
 * @version 2.0
 */
public class EquationsApp {
    /**
     * Головний метод програми.
     *
     * @param args Аргументи командного рядка (не використовуються).
     */
    public static void main(String[] args) {
        String fName = "result.txt";

        try (Scanner in = new Scanner(System.in);
             PrintWriter fout = new PrintWriter(new File(fName))) {

            try {
                Equations eq = new Equations();
                out.print("Enter X (in degrees): ");
                int x = in.nextInt();
                double result = eq.calculate(x);

                // Записуємо результат у файл і виводимо повідомлення в консоль
                fout.print(result);
                out.println("Result successfully saved to file " + fName);

            } catch (CalcException | java.util.InputMismatchException ex) {
                // Перехоплення помилок обчислення
                String errorMessage = ex.getMessage();
                out.println(errorMessage);
                fout.print(errorMessage);
            }
//            } catch (java.util.InputMismatchException ex) {
//                String errorMessage = "Error: Please enter an integer value for X.";
//                out.println(errorMessage);
//                fout.print(errorMessage);
//            }
        } catch (FileNotFoundException ex) {
            out.println("Exception reason: Could not create the file " + fName + ". Check permissions.");
        }
    }
}