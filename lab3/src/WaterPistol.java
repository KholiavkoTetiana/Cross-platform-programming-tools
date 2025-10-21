
/**
 * Водяний пістолет — підклас Pistol, що використовує "магазин" як резервуар води.
 * Реалізує інтерфейс Fillable.
 */
public class WaterPistol extends Pistol implements Fillable {
    // Кількість "вода-одиниць" витрачається за один спуск (наприклад, 10 умовних одиниць)
    private final int waterPerShot;

    /**
     * Конструктор.
     *
     * @param model модель
     * @param serial серійний номер
     * @param barrel ствол (можна передати null, але краще створити)
     * @param waterTank магазин, тут інтерпретуємо як резервуар для води
     * @param sight приціл
     * @param logger логер
     * @param waterPerShot одиниць води за один спуск
     */
    public WaterPistol(String model, String serial, Barrel barrel, Magazine waterTank, Sight sight, ActionLogger logger, int waterPerShot) {
        super(model, serial, barrel, waterTank, sight, logger);
        this.waterPerShot = Math.max(1, waterPerShot);
        logger.log("WaterPistol configured: waterPerShot=" + this.waterPerShot);
    }

    /**
     * Простий конструктор: стандартні компоненти.
     * @param logger логер
     */
    public WaterPistol(ActionLogger logger) {
        this("WaterG-100", "WAT-000", new Barrel(), new Magazine(200), new Sight("water-nozzle"), logger, 10);
    }

    /**
     * Виконує "вистріл" водяною струєю.
     * Перевіряє запобіжник, взведення та наявність води.
     */
    @Override
    public void fire() {
        // Якщо запобіжник увімкнено — не стріляємо
        if (safetyOn) {
            logger.log("Attempted spray: safety ON");
            return;
        }
        // Якщо не взведено — dryFire
        if (!slideCocked) {
            logger.log("Attempted spray: nozzle not cocked -> dryFire");
            dryFire();
            return;
        }
        // Якщо мало води — повідомляємо
        int current = magazine.getRoundsLoaded();
        if (current < waterPerShot) {
            logger.log("Attempted spray: not enough water (have=" + current + ", need=" + waterPerShot + ")");
            return;
        }
        // "Використовуємо" воду
        int toConsume = waterPerShot;
        while (toConsume-- > 0) magazine.unloadRound();
        slideCocked = false; // після спуску — скидаємо взведення
        logger.log("Sprayed water: consumed=" + waterPerShot + ", remaining=" + magazine.getRoundsLoaded());
    }

    /**
     * Заповнити резервуар водою (інтерпретуємо через reload()).
     * @param amount кількість одиниць для додавання
     * @return фактично додано одиниць
     */
    @Override
    public int fill(int amount) {
        if (amount <= 0) return 0;
        int before = magazine.getRoundsLoaded();
        int target = Math.min(magazine.getCapacity(), before + amount);
        magazine.reload(target); // перезаписує рівень на target
        int added = magazine.getRoundsLoaded() - before;
        logger.log("Filled water: added=" + added + ", now=" + magazine.getRoundsLoaded());
        return added;
    }

    @Override
    public int currentLevel() {
        int lvl = magazine.getRoundsLoaded();
        logger.log("Queried water level: " + lvl);
        return lvl;
    }

    /**
     * Залишаємо додатковий метод: "бризкнути" багато разів — для демонстрацій.
     * @param times кількість спусків
     */
    public void sprayMultiple(int times) {
        for (int i = 0; i < times; i++) {
            if (magazine.getRoundsLoaded() < waterPerShot) {
                logger.log("sprayMultiple: insufficient water at iteration " + i);
                break;
            }
            cockSlide();
            fire();
        }
    }
}
