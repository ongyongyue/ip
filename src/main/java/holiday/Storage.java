package holiday;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles reading and writing tasks to the data file.
 */
public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the data file.
     *
     * @return list of tasks loaded from file
     * @throws IOException if file reading fails
     */
    public List<Task> loadTasksFromFile() throws IOException {
        ensureDataFileExists();
        List<String> lines = Files.readAllLines(this.filePath, StandardCharsets.UTF_8);

        List<Task> tasks = new ArrayList<>();
        for (String line : lines) {
            if (line.isBlank()) continue;
            try {
                String[] parts = Parser.parseSavedTasks(line);
                if (parts[0].equals("T")) {
                    tasks.add(new ToDos(parts[1]));
                } else if (parts[0].equals("D")) {
                    tasks.add(new Deadlines(parts[1], parts[2]));
                } else if (parts[0].equals("E")) {
                    tasks.add(new Events(parts[1], parts[2], parts[3]));
                }
            } catch (Exception e) {
                System.out.println("Skipping corrupted line: " + line);
            }
        }
        return tasks;
    }

    /**
     * Saves tasks to the data file.
     *
     * @param tasks list of tasks to save
     * @throws IOException if file writing fails
     */
    public void saveTasksToFile(List<Task> tasks) throws IOException {
        ensureDataFileExists();
        List<String> lines = new ArrayList<>();
        for (Task t : tasks) {
            lines.add(t.toFileString());
        }
        Files.write(this.filePath, lines, StandardCharsets.UTF_8,
                StandardOpenOption.TRUNCATE_EXISTING);
    }

    /**
     * Ensures the data directory and file exist.
     *
     * @throws IOException if file creation fails
     */
    private void ensureDataFileExists() throws IOException {
        Path dir = filePath.getParent();
        if (dir != null && Files.notExists(dir)) {
            Files.createDirectories(dir);
        }
        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }
    }
}