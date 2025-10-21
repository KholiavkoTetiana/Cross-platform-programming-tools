
/**
 * Магазин — компонент, що зберігає кількість патронів.
 */
public class Magazine {
    private final int capacity;
    private int roundsLoaded;

    /** Конструктор. */
    public Magazine(int capacity) {
        this.capacity = Math.max(0, capacity);
        this.roundsLoaded = 0;
    }

    public boolean loadRound() {
        if (roundsLoaded < capacity) {
            roundsLoaded++;
            return true;
        }
        return false;
    }

    public boolean unloadRound() {
        if (roundsLoaded > 0) {
            roundsLoaded--;
            return true;
        }
        return false;
    }

    public void reload(int rounds) {
        if (rounds < 0) rounds = 0;
        roundsLoaded = Math.min(capacity, rounds);
    }

    public int getCapacity() { return capacity; }
    public int getRoundsLoaded() { return roundsLoaded; }
}
