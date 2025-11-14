/**
 * Приціл (Sight). Тільки тип для простоти.
 */
public class Sight {
    private final String type;

    /**
     * Конструктор з типом прицілу; якщо не вказано — "iron".
     * @param type тип прицілу
     */
    public Sight(String type) {
        this.type = (type == null || type.isEmpty()) ? "iron" : type;
    }

    /** No-arg конструктор, з дефолтним типом. */
    public Sight() { this("iron"); }

    public String getType() { return type; }

    @Override
    public String toString() {
        return "Sight{" + type + "}";
    }
}
