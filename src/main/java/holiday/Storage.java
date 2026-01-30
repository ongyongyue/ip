package holiday;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /*
    This function reads the saved .txt file and reinitializes any saved Tasks into Task instances
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

    /*
    Turn Tasks into txt form and save it into a txt file
     */
    public void saveTasksToFile(List<Task> tasks) throws IOException {
        ensureDataFileExists();
        List<String> lines = new ArrayList<>();
        for (Task t : tasks) {
            lines.add(t.toFileString()); // you will add this method in Task
        }
        Files.write(this.filePath, lines, StandardCharsets.UTF_8,
                StandardOpenOption.TRUNCATE_EXISTING);
    }

    /*
    Check if the data file exits, if it doesn't create the file and data directory
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
