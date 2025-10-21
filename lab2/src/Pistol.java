
/**
 * Клас, що моделює пістолет. Містить об'єктні поля (Barrel, Magazine, Sight),
 * кілька конструкторів та набір методів. Усі дії логуються через ActionLogger.
 */
public class Pistol {
    private final String model;
    private final String serial;

    private Barrel barrel;     // поле-об'єкт 1
    private Magazine magazine; // поле-об'єкт 2
    private Sight sight;       // поле-об'єкт 3

    private boolean safetyOn;
    private boolean slideCocked;

    private final ActionLogger logger;

    /**
     * Конструктор з параметрами.
     * @param model модель
     * @param serial серійний номер
     * @param barrel ствол
     * @param magazine магазин
     * @param sight приціл
     * @param logger логер (не null)
     */
    public Pistol(String model, String serial, Barrel barrel, Magazine magazine, Sight sight, ActionLogger logger) {
        if (logger == null) throw new IllegalArgumentException("Logger не може бути null");
        this.model = model;
        this.serial = serial;
        this.barrel = barrel;
        this.magazine = magazine;
        this.sight = sight;
        this.logger = logger;
        this.safetyOn = true;
        this.slideCocked = false;
        logger.log("Pistol created: " + model + " " + serial);
    }

    /** Конструктор спрощений (за замовчуванням компоненти). */
    public Pistol(ActionLogger logger) {
        this("Model-Default", "SN-000", new Barrel(), new Magazine(10), new Sight("iron"), logger);
    }

    // >=10 методів, усі логують свої дії

    public boolean isSafetyOn() {
        logger.log("Checked safety: " + safetyOn);
        return safetyOn;
    }

    public void toggleSafety() {
        safetyOn = !safetyOn;
        logger.log("Toggled safety -> " + safetyOn);
    }

    public void cockSlide() {
        slideCocked = true;
        logger.log("Slide cocked");
    }

    public void decockSlide() {
        slideCocked = false;
        logger.log("Slide decocked");
    }

    public void aimAt(String target) {
        logger.log("Aim at: " + (target == null ? "unknown" : target) + " with sight " + sight.getType());
    }

    public boolean loadRound() {
        boolean ok = magazine.loadRound();
        logger.log("loadRound -> " + ok + " (loaded=" + magazine.getRoundsLoaded() + ")");
        return ok;
    }

    public boolean unloadRound() {
        boolean ok = magazine.unloadRound();
        logger.log("unloadRound -> " + ok + " (loaded=" + magazine.getRoundsLoaded() + ")");
        return ok;
    }

    public void reloadMagazine(int rounds) {
        magazine.reload(rounds);
        logger.log("reloadMagazine -> loaded=" + magazine.getRoundsLoaded());
    }

    public void fire() {
        if (safetyOn) {
            logger.log("Attempted fire: safety ON");
            return;
        }
        if (!slideCocked) {
            logger.log("Attempted fire: slide not cocked -> dryFire");
            dryFire();
            return;
        }
        if (magazine.getRoundsLoaded() == 0) {
            logger.log("Attempted fire: magazine empty");
            return;
        }
        magazine.unloadRound();
        slideCocked = false;
        logger.log("Fired one round. Remaining: " + magazine.getRoundsLoaded());
    }

    public void dryFire() {
        logger.log("Dry fire (no round)");
    }

    public void attachSight(Sight newSight) {
        this.sight = newSight;
        logger.log("Attached sight: " + newSight.getType());
    }

    public void detachSight() {
        this.sight = new Sight("iron");
        logger.log("Detached sight, now: " + sight.getType());
    }

    public String getStatus() {
        String status = String.format("Pistol[%s %s] safety=%s cocked=%s mag=%d/%d sight=%s",
                model, serial, safetyOn, slideCocked, magazine.getRoundsLoaded(), magazine.getCapacity(), sight.getType());
        logger.log("Status requested: " + status);
        return status;
    }
}
