package holiday;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    @TempDir
    Path tempDir;

    @Test
    public void load_fileMissing_returnsEmptyList() throws Exception {
        Path file = tempDir.resolve("holiday.txt");
        Storage storage = new Storage(file.toString());

        // if your Storage creates the file automatically, load should succeed
        List<Task> tasks = storage.loadTasksFromFile();
        assertNotNull(tasks);
        assertEquals(0, tasks.size());
    }

    @Test
    public void saveThenLoad_roundTrip_preservesTasks() throws Exception {
        Path file = tempDir.resolve("holiday.txt");
        Storage storage = new Storage(file.toString());

        List<Task> toSave = List.of(
                new ToDos("read book"),
                new Deadlines("return book", "2019-09-02 1800")
        );

        storage.saveTasksToFile(toSave);
        List<Task> loaded = storage.loadTasksFromFile();

        assertEquals(2, loaded.size());
        assertEquals(toSave.get(0).toString(), loaded.get(0).toString());
        assertEquals(toSave.get(1).toString(), loaded.get(1).toString());
    }

    @Test
    public void load_corruptedLine_skipsLine() throws Exception {
        Path file = tempDir.resolve("holiday.txt");

        java.nio.file.Files.writeString(file,
                "D,stuff ,2019-08-29 1909\n" +
                        "THIS IS BROKEN\n" +
                        "T,another task\n");

        Storage storage = new Storage(file.toString());
        List<Task> loaded = storage.loadTasksFromFile();

        assertEquals(2, loaded.size());
    }
}
