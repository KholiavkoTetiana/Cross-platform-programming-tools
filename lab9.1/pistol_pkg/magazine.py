class Magazine:
    """Резервуар для води або куль."""

    def __init__(self, capacity=100):
        self.capacity = capacity
        self.rounds_loaded = 0

    def available(self):
        return self.rounds_loaded

    def reload(self, amount):
        self.rounds_loaded = min(self.capacity, amount)

    def unload_round(self):
        if self.rounds_loaded > 0:
            self.rounds_loaded -= 1

    def getRoundsLoaded(self):
        return self.rounds_loaded

    def getCapacity(self):
        return self.capacity
