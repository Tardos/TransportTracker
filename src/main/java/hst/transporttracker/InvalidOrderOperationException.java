package hst.transporttracker;

public class InvalidOrderOperationException extends RuntimeException
{
    private ErrorCode errorCode;
    private String errorText;
    private Order order;

    public InvalidOrderOperationException(ErrorCode errorCode, String errorText, Order order) {
        this.errorCode = errorCode;
        this.errorText = errorText;
        this.order = order;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public String getErrorText() {
        return this.errorText;
    }

    public Order getOrder() {
        return this.order;
    }
}
