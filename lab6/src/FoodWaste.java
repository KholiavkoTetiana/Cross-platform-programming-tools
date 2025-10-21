
/**
 * Клас, що представляє харчові відходи.
 * Реалізує інтерфейс Waste.
 */
public class FoodWaste implements Waste {
    private String name;
    private double volume; // в кубічних сантиметрах
    public static int counter;

    /**
     * Конструктор для створення об'єкта харчових відходів.
     * @param name Назва продукту (наприклад, "Яблучний огризок").
     * @param volume Об'єм відходів.
     */
    public FoodWaste(String name, double volume) {
        this.name = name;
        this.volume = volume;
        counter++;
    }

    @Override
    public double getVolume() {
        return volume;
    }

    @Override
    public void printInfo() {
        System.out.println("Харчові відходи: " + name + ", Об'єм: " + volume + " см³.");
    }

    /**
     * Порівнює поточний об'єкт сміття з іншим за об'ємом.
     * @param other Інший об'єкт сміття для порівняння.
     * @return 1 якщо поточний об'єкт більший, -1 якщо менший, 0 якщо рівні.
     */
    @Override
    public int compareTo(Waste other) {
        return Double.compare(this.volume, other.getVolume());
    }
}