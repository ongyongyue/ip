package holiday;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    /*
    A test that checks if code detects blank Inputs
     */
    @Test
    public void parse_blankInput_throwsException() {
        assertThrows(HolidayException.class, () -> Parser.parseCommand(""));
    }
    /*
    A test that checks if code parses valid commands correctly
     */
    @Test
    public void parse_validCommand_splitsCorrectly() throws Exception {
        String[] parts = Parser.parseCommand("todo read book");
        assertEquals("todo", parts[0]);
        assertEquals("read book", parts[1]);
    }
    /*
    A test that checks if code detects an invalid parsed command
     */
    @Test
    public void parse_noDescriptionForTodo_throwsException() {
        // if your parser checks this; if not, test the method/class that does
        assertThrows(HolidayException.class, () -> Parser.parseCommand("todo"));
    }
}
