from datetime import datetime

class ActionLogger:
    """Логер дій пістолета — запис у файл."""

    def __init__(self):
        self.file = None

    def __enter__(self):
        self.file = open("waterpistol_log.txt", "a", encoding="utf-8")
        return self

    def __exit__(self, exc_type, exc_value, traceback):
        if self.file:
            self.file.close()

    def log(self, message: str):
        if self.file:
            timestamp = datetime.now().strftime("[%Y-%m-%d %H:%M:%S]")
            self.file.write(f"{timestamp} {message}\n")
