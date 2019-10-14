package ke.co.dynamodigital.commons.exceptions;

/**
 * @author Bibibiu
 * created 10/13/19 at 21:01
 **/
public class ServiceUnavailableException extends Exception {
    public ServiceUnavailableException(String message) {
        super(message);
    }

    public ServiceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
