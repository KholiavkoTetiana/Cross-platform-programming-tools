import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Простий логер для запису дій у файл. Реалізує AutoCloseable для коректного закриття.
 */
public class ActionLogger implements AutoCloseable {
    private final BufferedWriter writer;

    /**
     * Конструктор логера.
     * @param filename ім'я файлу для логів
     * @throws IOException якщо файл не відкривається
     */
    public ActionLogger(String filename) throws IOException {
        this.writer = new BufferedWriter(new FileWriter(filename, true)); // append = true
    }

    /**
     * Записує текстовий рядок у лог із часовим штампом.
     * Синхронізовано для безпечного використання з кількох потоків.
     * @param message повідомлення
     */
    public synchronized void log(String message) {
        try {
            writer.write("[" + LocalDateTime.now() + "] " + message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            // Логування не повинно ламати основну програму — пишемо у stderr.
            System.err.println("Не вдалося записати лог: " + e.getMessage());
        }
    }

    /**
     * Закриває відкритий writer; викликається автоматично в try-with-resources.
     * @throws IOException при помилці закриття
     */
    @Override
    public void close() throws IOException {
        writer.close();
    }
}
