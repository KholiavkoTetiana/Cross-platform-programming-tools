/****************************************************************************
 * Copyright (c) 2023-2025 Lviv Polytechnic National University. All Rights Reserved.
 *
 * This program and the accompanying materials are made available under the terms
 * of the Academic Free License v. 3.0 which accompanies this distribution, and is
 * available at https://opensource.org/license/afl-3-0-php/
 *
 * SPDX-License-Identifier: AFL-3.0
 ****************************************************************************/


/**
 * Клас <code>CalcException</code> уточнює виняток {@link ArithmeticException}.
 * Використовується для обробки помилок під час обчислення виразу.
 *
 * @author Andriy Ivanenko
 * @version 1.0
 */
class CalcException extends ArithmeticException {
    /**
     * Конструктор без параметрів.
     */
    public CalcException() {
    }

    /**
     * Конструктор, що приймає повідомлення про причину винятку.
     *
     * @param cause Причина виникнення помилки.
     */
    public CalcException(String cause) {
        super(cause);
    }
}

/**
 * Клас <code>Equations</code> реалізує метод для обчислення виразу
 * y = sin(3x - 5) / ctg(2x).
 *
 * @author Andriy Ivanenko
 * @version 1.0
 */
public class Equations {
    /**
     * Метод для обчислення виразу y = sin(3x - 5) / ctg(2x).
     *
     * @param x Кут в градусах.
     * @return Результат обчислення виразу.
     * @throws CalcException Виняток, що виникає при неможливості обчислення.
     */
    public double calculate(int x) throws CalcException {
        double y, rad;
        rad = Math.toRadians(x);

        try {
            double numerator = Math.sin(3 * rad - 5);
            // ctg(2x) = 1 / tan(2x)
            double cotangent = 1.0 / Math.tan(2 * rad);

            if (Math.abs(cotangent) < 1e-10) {
                throw new ArithmeticException();
            }

            y = numerator / cotangent;

            // Котангенс невизначений, якщо його аргумент (2x) є кратним 90 градусам.
            if (Double.isNaN(y) || Double.isInfinite(y) || x % 90 == 0) {
                throw new ArithmeticException();
            }
        } catch (ArithmeticException ex) {
            if (x % 90 == 0) {
                throw new CalcException("Exception reason: Illegal value of X for cotangent calculation. X cannot be a multiple of 90 degrees.");
            } else if (Math.abs(1.0 / Math.tan(2 * rad)) < 1e-10) {
                throw new CalcException("Exception reason: Division by zero (ctg(2x) is zero).");
            } else {
                throw new CalcException("Unknown reason for the exception during calculation.");
            }
        }
        return y;
    }
}