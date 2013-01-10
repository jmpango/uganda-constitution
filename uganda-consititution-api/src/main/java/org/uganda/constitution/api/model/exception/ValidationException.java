package org.uganda.constitution.api.model.exception;

/**
 *  Represents an exception thrown after a validation.
 *
 * @author Jonathan
 */
public class ValidationException extends Exception {

    public ValidationException() {
        super();
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }
}
