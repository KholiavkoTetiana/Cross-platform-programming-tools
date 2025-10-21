
public class TrashCanDriver {

    /**
     * Головний метод програми.
     * @param args Аргументи командного рядка (не використовуються).
     */
    public static void main(String[] args) {
        // Створюємо екземпляр сміттєвого бака, який може містити будь-який об'єкт типу Waste
        TrashCan<Waste> myTrashCan = new TrashCan<>();

        System.out.println("Наповнення сміттєвого бака...");

        // Додаємо екземпляри двох різних класів
        myTrashCan.addWaste(new FoodWaste("Бананова шкірка", 150.5));
        myTrashCan.addWaste(new Plastic("Пляшка PET 1.5л", 1500.0, true));
        myTrashCan.addWaste(new FoodWaste("Яблучний огризок", 75.2));
        myTrashCan.addWaste(new Plastic("Поліетиленовий пакет", 300.0, false));

        // Показуємо, що знаходиться в баку
        myTrashCan.showContents();


        Waste maxWaste = myTrashCan.findMaxVolumeWaste();
        System.out.print("Найбільший елемент: ");
        maxWaste.printInfo();


        // Демонстрація виймання елемента
        myTrashCan.removeWaste(2);
        myTrashCan.showContents();

        // Демонстрація очищення бака
        myTrashCan.emptyTrashCan();
        myTrashCan.showContents();
    }
}