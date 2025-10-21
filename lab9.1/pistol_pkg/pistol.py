from .barrel import Barrel
from .magazine import Magazine
from .sight import Sight
from .logger import ActionLogger

class Pistol:
    """Базовий клас пістолета."""

    def __init__(self, model: str, serial: str, barrel: Barrel, magazine: Magazine, sight: Sight, logger: ActionLogger):
        self.model = model
        self.serial = serial
        self.barrel = barrel
        self.magazine = magazine
        self.sight = sight
        self.logger = logger

        self.safety_on = True
        self.slide_cocked = False
        logger.log(f"Pistol created: {model} {serial}")

    def set_safety(self, on: bool):
        self.safety_on = on
        self.logger.log(f"Set safety -> {self.safety_on}")

    def cock_slide(self):
        self.slide_cocked = True
        self.logger.log("Slide cocked")

    def dry_fire(self):
        self.logger.log("Dry fire (no round)")

    def get_status(self):
        return f"Pistol[{self.model} {self.serial}] safety={self.safety_on} cocked={self.slide_cocked} mag={self.magazine.getRoundsLoaded()}/{self.magazine.getCapacity()} sight={self.sight.type}"
