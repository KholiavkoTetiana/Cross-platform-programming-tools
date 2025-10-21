/****************************************************************************
 * Copyright (c) 2023-2025 Lviv Polytechnic National University. All Rights Reserved.
 *
 * This program and the accompanying materials are made available under the terms
 * of the Academic Free License v. 3.0 which accompanies this distribution, and is
 * available at https://opensource.org/license/afl-3-0-php/
 *
 * SPDX-License-Identifier: AFL-3.0
 ****************************************************************************/

import java.io.*;
import java.util.Scanner;

/**
 * Клас <code>ResultManager</code> інкапсулює логіку обчислення виразу
 * з класу {@link Equations} та надає методи для збереження і завантаження
 * результату в текстовому та двійковому форматах.
 *
 * @author Andriy Ivanenko
 * @version 1.0
 */
public class ResultManager {
    private double result;
    private final Equations equations = new Equations();

    /**
     * Обчислює вираз y = sin(3x - 5) / ctg(2x) і зберігає результат.
     *
     * @param x Вхідне значення X (кут в градусах).
     * @throws CalcException Якщо під час обчислення виникає помилка.
     */
    public void calculate(int x) throws CalcException {
        result = equations.calculate(x);
    }

    /**
     * Повертає збережений результат обчислень.
     *
     * @return Результат типу double.
     */
    public double getResult() {
        return result;
    }

    /**
     * Записує результат у текстовий файл.
     *
     * @param fName Назва файлу для збереження.
     * @throws FileNotFoundException Якщо файл не може бути створений.
     */
    public void writeResTxt(String fName) throws FileNotFoundException {
        try (PrintWriter f = new PrintWriter(fName)) {
            f.printf("%f", result);
        }
    }

    /**
     * Читає результат з текстового файлу.
     *
     * @param fName Назва файлу для читання.
     * @throws FileNotFoundException Якщо файл не знайдено.
     */
    public void readResTxt(String fName) throws FileNotFoundException {
        File f = new File(fName);
        if (f.exists()) {
            try (Scanner s = new Scanner(f)) {
                result = s.nextDouble();
            }
        } else {
            throw new FileNotFoundException("File " + fName + " not found");
        }
    }

    /**
     * Записує результат у двійковий файл.
     *
     * @param fName Назва файлу для збереження.
     * @throws IOException Якщо виникає помилка запису.
     */
    public void writeResBin(String fName) throws IOException {
        try (DataOutputStream f = new DataOutputStream(new FileOutputStream(fName))) {
            f.writeDouble(result);
        }
    }

    /**
     * Читає результат з двійкового файлу.
     *
     * @param fName Назва файлу для читання.
     * @throws IOException Якщо виникає помилка читання.
     */
    public void readResBin(String fName) throws IOException {
        try (DataInputStream f = new DataInputStream(new FileInputStream(fName))) {
            result = f.readDouble();
        }
    }
}