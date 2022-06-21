package cz.muni.fi.pb162.project.exception;

/**
 * Exception when there is nothing painted on the drawing object.
 *
 * @author Michal Soltis
 */
public class EmptyDrawableException extends Exception {

    /**
     * Calls parent's constructor
     *
     * @param message the detail message
     */
    public EmptyDrawableException(String message) {
        super(message);
    }

    /**
     * Calls parent's constructor
     *
     * @param message the detail message
     * @param cause   the cause
     */
    public EmptyDrawableException(String message, Throwable cause) {
        super(message, cause);
    }
}
