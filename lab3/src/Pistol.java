
/**
 * Абстрактний суперклас, що моделює загальні властивості пістолета.
 * Реалізує базову поведінку; конкретний тип вистрілу реалізує підклас.
 */
public abstract class Pistol {
    protected final String model;
    protected final String serial;
    protected Barrel barrel;
    protected Magazine magazine;
    protected Sight sight;

    protected boolean safetyOn;
    protected boolean slideCocked;

    protected final ActionLogger logger;

    /**
     * Конструктор з параметрами.
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

    /**
     * Перевіряє стан запобіжника.
     * @return true якщо запобіжник увімкнено
     */
    public boolean isSafetyOn() {
        logger.log("Checked safety: " + safetyOn);
        return safetyOn;
    }

    /**
     * Явно встановити стан запобіжника.
     * @param on true — увімкнути
     */
    public void setSafety(boolean on) {
        this.safetyOn = on;
        logger.log("Set safety -> " + safetyOn);
    }

    /**
     * Перемикає запобіжник.
     */
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

    /**
     * Абстрактний метод "вистрілити" — конкретний ефект залежить від підкласу.
     */
    public abstract void fire();

    public void dryFire() {
        logger.log("Dry fire (no round)");
    }

    public String getStatus() {
        String status = String.format("Pistol[%s %s] safety=%s cocked=%s mag=%d/%d sight=%s",
                model, serial, safetyOn, slideCocked, magazine.getRoundsLoaded(), magazine.getCapacity(), sight.getType());
        logger.log("Status requested: " + status);
        return status;
    }

    @Override
    public String toString() {
        return String.format("Pistol{model=%s, serial=%s, %s, %s, %s}", model, serial, barrel, magazine, sight);
    }
}
