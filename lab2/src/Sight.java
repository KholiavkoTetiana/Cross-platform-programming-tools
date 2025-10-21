
/**
 * Приціл — простий компонент (тип прицілу як рядок).
 */
public class Sight {
    private final String type;

    public Sight(String type) {
        this.type = (type == null || type.isEmpty()) ? "iron" : type;
    }

    public String getType() { return type; }
}
