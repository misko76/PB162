package cz.muni.fi.pb162.project.exception;

/**
 * Unchecked exception when there are not enough vertices in the collection.
 *
 * @author Michal Soltis
 */
public class MissingVerticesException extends RuntimeException {

    /**
     * Calls parent's constructor
     *
     * @param message the detail message
     */
    public MissingVerticesException(String message) {
        super(message);
    }

    /**
     * Calls parent's constructor
     *
     * @param message the detail message
     * @param cause   the cause
     */
    public MissingVerticesException(String message, Throwable cause) {
        super(message, cause);
    }
}
