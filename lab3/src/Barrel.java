
public class Barrel {
    private double lengthMm;
    private double caliberMm;

    /**
     * Створює ствол зі значеннями за замовчуванням.
     */
    public Barrel() {
        this(100.0, 9.0);
    }

    /**
     * Конструктор зі значеннями.
     * @param lengthMm довжина в мм
     * @param caliberMm калібр в мм
     */
    public Barrel(double lengthMm, double caliberMm) {
        this.lengthMm = lengthMm;
        this.caliberMm = caliberMm;
    }

    public double getLengthMm() { return lengthMm; }
    public double getCaliberMm() { return caliberMm; }

    @Override
    public String toString() {
        return String.format("Barrel[length=%.1fmm, caliber=%.1fmm]", lengthMm, caliberMm);
    }
}
