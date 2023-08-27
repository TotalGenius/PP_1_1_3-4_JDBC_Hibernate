package jm.task.core.jdbc.exceptions;

public class SQLOperationException extends RuntimeException {
    public SQLOperationException(Throwable throwable) {
        super(throwable);
    }

    public SQLOperationException(String message) {
        super(message);
    }
}
