import java.io.IOException;

public class PistolDriver {
    public static void main(String[] args) {
        try (ActionLogger logger = new ActionLogger("pistol_log.txt")) {

            // дефолтні параметри
            Pistol defaultPistol = new Pistol(logger);
            logger.log("=== Тестуємо пістолет за замовчуванням ===");
            defaultPistol.toggleSafety();
            defaultPistol.reloadMagazine(5);
            defaultPistol.cockSlide();
            defaultPistol.fire();
            defaultPistol.cockSlide();
            defaultPistol.fire();
            defaultPistol.aimAt("target-50m");
            defaultPistol.attachSight(new Sight("red-dot"));
            System.out.println(defaultPistol.getStatus());
            defaultPistol.detachSight();


            Barrel customBarrel = new Barrel();
            Magazine customMagazine = new Magazine(15);
            Sight customSight = new Sight("holographic");

            Pistol customPistol = new Pistol(
                    "Glock-19",
                    "SN-123456",
                    customBarrel,
                    customMagazine,
                    customSight,       // приціл
                    logger
            );

            logger.log("=== Тестуємо пістолет з кастомними параметрами ===");
            customPistol.toggleSafety();
            customPistol.reloadMagazine(10);
            customPistol.cockSlide();
            customPistol.fire();
            customPistol.aimAt("target-100m");
            customPistol.detachSight();
            System.out.println(customPistol.getStatus());

            System.out.println("Демонстрація завершена. Перевірте pistol_log.txt");

        } catch (IOException e) {
            System.err.println("Не вдалося відкрити лог-файл: " + e.getMessage());
        }
    }
}
