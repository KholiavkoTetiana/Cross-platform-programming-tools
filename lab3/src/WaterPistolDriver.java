
import java.io.IOException;

/**
 * Клас-драйвер для демонстрації WaterPistol.
 */
public class WaterPistolDriver {
    public static void main(String[] args) {
        try (ActionLogger logger = new ActionLogger("waterpistol_log.txt")) {
            // Створюємо водяний пістолет зі стандартними параметрами та наповнюємо його
            WaterPistol wp = new WaterPistol(logger);
            logger.log("=== Demo: default WaterPistol ===");

            wp.fill(100);           // додаємо 100 одиниць води
            wp.setSafety(false);    // відключаємо запобіжник
            wp.cockSlide();         // взводимо
            wp.fire();              // один спрей
            wp.cockSlide();
            wp.fire();
            wp.aimAt("garden target");
            wp.fill(50);
            wp.sprayMultiple(3);

            // Кастомний водяний пістолет з більшою витратою на постріл
            Magazine bigTank = new Magazine(500);
            WaterPistol heavy = new WaterPistol("HydroMax", "W-999", new Barrel(120, 6.0), bigTank, new Sight("wide-nozzle"), logger, 30);
            logger.log("=== Demo: heavy WaterPistol ===");
            heavy.fill(200);
            heavy.setSafety(false);
            heavy.cockSlide();
            heavy.fire();

            System.out.println("Demo finished — check waterpistol_log.txt");

        } catch (IOException e) {
            System.err.println("Cannot open log file: " + e.getMessage());
        }
    }
}
