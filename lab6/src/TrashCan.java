
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Параметризований клас, що реалізує сміттєвий бак.
 * Може зберігати будь-які об'єкти, що реалізують інтерфейс Waste.
 * @param <T> Тип сміття, що може зберігатися в баку.
 */
public class TrashCan<T extends Waste> {

    private final List<T> contents;

    /**
     * Конструктор для створення порожнього сміттєвого бака.
     */
    public TrashCan() {
        contents = new ArrayList<>();
    }

    /**
     * Додає сміття в бак.
     * @param item Елемент сміття для додавання.
     */
    public void addWaste(T item) {
        contents.add(item);
        System.out.print("-> Додано елемент: ");
        item.printInfo();
    }

    /**
     * Виймає сміття з бака за індексом.
     * @param index Індекс елемента, який потрібно вийняти.
     * @return Вийнятий елемент.
     * @throws IndexOutOfBoundsException якщо індекс виходить за межі списку.
     */
    public T removeWaste(int index) {
        if (index >= 0 && index < contents.size()) {
            T removedItem = contents.remove(index);
            System.out.print("<- Вийнято елемент: ");
            removedItem.printInfo();
            return removedItem;
        }
        throw new IndexOutOfBoundsException("Неправильний індекс для виймання: " + index);
    }



    public T findMaxVolumeWaste() {

        T maxItem = contents.get(0);
        for (int i = 1; i < contents.size(); i++) {
            if (contents.get(i).compareTo(maxItem) > 0 ) {
                maxItem = contents.get(i);
            }
        }
        return maxItem;
    }

    /**
     * Повністю очищує сміттєвий бак.
     */
    public void emptyTrashCan() {
        contents.clear();
        System.out.println("\n*** Сміттєвий бак було повністю очищено! ***\n");
    }

    /**
     * Показує весь вміст сміттєвого бака.
     */
    public void showContents() {
        System.out.println("\n--- Вміст сміттєвого бака ---");
        if (contents.isEmpty()) {
            System.out.println("Бак порожній.");
        } else {
            for (T item : contents) {
                item.printInfo();
            }
        }
        System.out.println("-----------------------------\n");
    }
}