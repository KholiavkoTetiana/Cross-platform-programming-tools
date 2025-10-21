from typing import Optional
from .pistol import Pistol
from .barrel import Barrel
from .magazine import Magazine
from .sight import Sight
from .logger import ActionLogger

class WaterPistol(Pistol):
    """Водяний пістолет — споживає воду на один постріл."""

    def __init__(self, model: str, serial: str, barrel: Optional[Barrel], water_tank: Magazine, sight: Sight, logger: ActionLogger, water_per_shot: int = 10):
        super().__init__(model, serial, barrel, water_tank, sight, logger)
        self.water_per_shot = max(1, water_per_shot)
        self.logger.log(f"WaterPistol configured: water_per_shot={self.water_per_shot}")

    @classmethod
    def default(cls, logger: ActionLogger):
        return cls(
            model="WaterG-100",
            serial="WAT-000",
            barrel=Barrel(),
            water_tank=Magazine(200),
            sight=Sight("water-nozzle"),
            logger=logger,
            water_per_shot=10
        )

    def fire(self):
        if self.safety_on:
            self.logger.log("Attempted spray: safety ON")
            return
        if not self.slide_cocked:
            self.logger.log("Attempted spray: nozzle not cocked -> dryFire")
            self.dry_fire()
            return
        if self.magazine.available() < self.water_per_shot:
            self.logger.log(f"Attempted spray: not enough water (have={self.magazine.available()}, need={self.water_per_shot})")
            return

        for _ in range(self.water_per_shot):
            self.magazine.unload_round()
        self.slide_cocked = False
        self.logger.log(f"Sprayed water: consumed={self.water_per_shot}, remaining={self.magazine.available()}")

    def fill(self, amount: int) -> int:
        before = self.magazine.available()
        self.magazine.reload(before + amount)
        added = self.magazine.available() - before
        self.logger.log(f"Filled water: added={added}, now={self.magazine.available()}")
        return added

    def current_level(self) -> int:
        lvl = self.magazine.available()
        self.logger.log(f"Queried water level: {lvl}")
        return lvl
