
/**
 * Ствол пістолета — простий клас-компонент.
 */
public class Barrel {
    private double lengthMm;
    private double caliberMm;

    /** Конструктор за замовчуванням. */
    public Barrel() {
        this.lengthMm = 100.0;
        this.caliberMm = 9.0;
    }

    /** Конструктор з параметрами. */
    public Barrel(double lengthMm, double caliberMm) {
        this.lengthMm = lengthMm;
        this.caliberMm = caliberMm;
    }

    public double getLengthMm() { return lengthMm; }
    public double getCaliberMm() { return caliberMm; }
}
