
import java.io.IOException;

/**
 * Клас-драйвер — демонструє роботу конкретного підкласу Pistol (WaterPistol).
 */
public class PistolDriver {
    public static void main(String[] args) {
        try (ActionLogger logger = new ActionLogger("pistol_log.txt")) {

            // 1) Default WaterPistol (короткий конструктор у WaterPistol)
            logger.log("=== Тест: default WaterPistol ===");
            WaterPistol defaultWp = new WaterPistol(logger);
            defaultWp.fill(100);           // наповнюємо
            defaultWp.setSafety(false);    // відключаємо запобіжник
            defaultWp.cockSlide();         // взводимо
            defaultWp.fire();              // спрацьовує водяний вистріл
            System.out.println(defaultWp.getStatus());

            // 2) Custom WaterPistol (повний конструктор) — приклад без значень за замовчуванням
            logger.log("=== Тест: custom WaterPistol ===");
            Barrel customBarrel = new Barrel(120.0, 6.0);
            Magazine bigTank = new Magazine(300);            // великий "бак" для води
            Sight customSight = new Sight("wide-nozzle");
            WaterPistol customWp = new WaterPistol(
                    "HydroMax",
                    "W-12345",
                    customBarrel,
                    bigTank,
                    customSight,
                    logger,
                    30              // waterPerShot — 30 одиниць на спуск
            );

            customWp.fill(200);
            customWp.setSafety(false);
            customWp.cockSlide();
            customWp.fire();
            System.out.println(customWp.getStatus());

            logger.log("=== Demo finished ===");
            System.out.println("Демонстрація завершена. Перевірте pistol_log.txt");

        } catch (IOException e) {
            System.err.println("Не вдалося відкрити лог-файл: " + e.getMessage());
        }
    }
}
