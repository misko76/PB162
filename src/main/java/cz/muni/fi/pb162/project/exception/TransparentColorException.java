package cz.muni.fi.pb162.project.exception;

/**
 * Exception when drawing in the same color on the same background, e.g., white pencil on white paper.
 *
 * @author Michal Soltis
 */
public class TransparentColorException extends Exception {

    /**
     * Calls parent's constructor
     *
     * @param message the detail message
     */
    public TransparentColorException(String message) {
        super(message);
    }

    /**
     * Calls parent's constructor
     *
     * @param message the detail message
     * @param cause   the cause
     */
    public TransparentColorException(String message, Throwable cause) {
        super(message, cause);
    }
}
