package service.exception;

public class BusinessException extends Exception {
    public BusinessException() {
            super();
        }

    public BusinessException(String message) {
            super(message);
        }

    public BusinessException(Exception exception) {
            super(exception);
        }

    public BusinessException(String message, Exception exception) {
            super(message, exception);
        }
    }
