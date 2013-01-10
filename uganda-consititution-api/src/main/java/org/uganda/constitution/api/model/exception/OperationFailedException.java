package org.uganda.constitution.api.model.exception;

/**
 * Represents an exception thrown when an operation fails.
 *
 * @author Jonathan
 */
public class OperationFailedException extends Exception {

    public OperationFailedException() {
    }

    public OperationFailedException(String message) {
        super(message);
    }

    public OperationFailedException(Throwable cause) {
        super(cause);
    }

    public OperationFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
