
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Простий логер, записує події у файл. Реалізує AutoCloseable для коректного закриття.
 */
public class ActionLogger implements AutoCloseable {
    private final BufferedWriter writer;

    /**
     * Відкриває файл для додавання логів.
     * @param filename ім'я файлу
     * @throws IOException якщо не вдається відкрити файл
     */
    public ActionLogger(String filename) throws IOException {
        this.writer = new BufferedWriter(new FileWriter(filename, true));
    }

    /**
     * Записує рядок у лог з часовим штампом.
     * @param message текст події
     */
    public synchronized void log(String message) {
        try {
            writer.write("[" + LocalDateTime.now() + "] " + message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.err.println("Логування не вдалося: " + e.getMessage());
        }
    }

    /**
     * Коректно закриває ресурс.
     * @throws IOException якщо сталася помилка при закритті
     */
    @Override
    public void close() throws IOException {
        writer.close();
    }
}
