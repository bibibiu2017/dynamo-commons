package ke.co.dynamodigital.commons.exceptions;

/**
 * @author Bibibiu
 * created 8/28/19 at 13:04
 **/
public class FeignException extends RuntimeException {

    public FeignException(String message) {
        super(message);
    }

    public FeignException(String message, Throwable cause) {
        super(message, cause);
    }
}
