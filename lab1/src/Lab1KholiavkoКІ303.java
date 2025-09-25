import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Лабораторна робота №1.
 * Генерація зубчастого масиву з символів-заповнювачів.
 *
 * @author Холявко Тетяна
 * @version 1.0
 */
public class Lab1KholiavkoКІ303 {

    /**
     * Конструктор класу Lab1KholiavkoКІ303.
     *Використовується за замовчуванням.
     */
    public Lab1KholiavkoКІ303() {
    }
    /**
     * Головний метод програми.
     * Запитує у користувача розмір квадратної матриці та символ-заповнювач,
     * формує зубчастий масив і виводить його у консоль та у текстовий файл.
     *
     * @param args аргументи командного рядка
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть розмір матриці: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введіть символ-заповнювач: ");
        String input = scanner.nextLine();

        if (input.length() != 1) {
            System.out.println("Помилка: потрібно ввести лише один символ!");
            return;
        }
        char symbol = input.charAt(0);

        char[][] array = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = (j < i) ?  ' ' :  symbol;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }

        try (FileWriter writer = new FileWriter("output.txt")) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    writer.write(array[i][j]);
                }
                writer.write("\n");
            }
            System.out.println("\nМасив збережено у файл output.txt");
        } catch (IOException e) {
            System.out.println("Помилка запису у файл: " + e.getMessage());
        }

    }
}
