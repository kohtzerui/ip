/**
 * Represents an exception specific to the Nimbus application.
 * Thrown when the application encounters an error in user input or operations.
 */
public class NimbusException extends Exception {

    /**
     * Constructs a new NimbusException with the specified error message.
     *
     * @param message The detail message describing the error.
     */
    public NimbusException(String message) {
        super(message);
    }
}
