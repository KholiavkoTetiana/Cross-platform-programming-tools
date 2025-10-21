from .logger import ActionLogger
from .water_pistol import WaterPistol

def demo():
    with ActionLogger() as logger:
        logger.log("=== Demo started ===")

        # 1) Default WaterPistol
        logger.log("=== Test: default WaterPistol ===")
        wp = WaterPistol.default(logger)
        wp.fill(100)
        wp.set_safety(False)
        wp.cock_slide()
        wp.fire()
        print(wp.get_status())

        # 2) Spray multiple
        logger.log("=== Test: spray multiple ===")
        wp.cock_slide()
        wp.fire()
        wp.cock_slide()
        wp.fire()
        print(wp.get_status())

if __name__ == "__main__":
    demo()
