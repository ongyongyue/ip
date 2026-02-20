package holiday;

/**
 * Represents an application-specific exception for the Holiday chatbot.
 */
public class HolidayException extends Exception {

    /**
     * Creates a HolidayException with a specified error message.
     *
     * @param message description of the error
     */
    HolidayException(String message) {
        super(message);
    }
}