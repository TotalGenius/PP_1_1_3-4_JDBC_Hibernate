package jm.task.core.jdbc.exceptions;

public class DBConnectionException extends RuntimeException {
    public DBConnectionException(Throwable throwable) {
        super(throwable);
    }

    public DBConnectionException(String message) {
        super(message);
    }
}
