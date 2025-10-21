
/**
 * Клас, що представляє пластикове сміття.
 * Реалізує інтерфейс Waste.
 */
public class Plastic implements Waste {
    private String type;
    private double volume; // в кубічних сантиметрах
    private boolean isRecyclable;
    public static int counter;


    /**
     * Конструктор для створення об'єкта пластикового сміття.
     * @param type Тип пластику (наприклад, "Пляшка PET").
     * @param volume Об'єм сміття.
     * @param isRecyclable Чи підлягає переробці.
     */
    public Plastic(String type, double volume, boolean isRecyclable) {
        this.type = type;
        this.volume = volume;
        this.isRecyclable = isRecyclable;
        counter++;
    }

    @Override
    public double getVolume() {
        return volume;
    }

    @Override
    public void printInfo() {
        String recyclableStatus = isRecyclable ? "так" : "ні";
        System.out.println("Пластик: " + type + ", Об'єм: " + volume + " см³, Підлягає переробці: " + recyclableStatus + ".");
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