
/**
 * Магазин — загальний контейнер для "боєприпасів".
 * Ми використовуємо його і для реального боєприпасу, і для води (як одиниці "вода-в-мілілітрах").
 */
public class Magazine {
    private final int capacity;
    private int roundsLoaded;

    /**
     * Конструктор.
     * @param capacity макс. кількість одиниць
     */
    public Magazine(int capacity) {
        this.capacity = Math.max(0, capacity);
        this.roundsLoaded = 0;
    }

    /**
     * Додає одну одиницю (патрон / умовний мілілітр-блок).
     * @return true, якщо вдалося додати
     */
    public boolean loadRound() {
        if (roundsLoaded < capacity) {
            roundsLoaded++;
            return true;
        }
        return false;
    }

    /**
     * Вилучає одну одиницю.
     * @return true, якщо було що вийняти
     */
    public boolean unloadRound() {
        if (roundsLoaded > 0) {
            roundsLoaded--;
            return true;
        }
        return false;
    }

    /**
     * Перезаряджає магазин певною кількістю одиниць (до capacity).
     * @param rounds кількість для завантаження
     */
    public void reload(int rounds) {
        if (rounds < 0) rounds = 0;
        roundsLoaded = Math.min(capacity, rounds);
    }

    public int getCapacity() { return capacity; }
    public int getRoundsLoaded() { return roundsLoaded; }

    @Override
    public String toString() {
        return String.format("Magazine{loaded=%d/%d}", roundsLoaded, capacity);
    }
}
