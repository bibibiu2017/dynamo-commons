package ke.co.dynamodigital.commons.exceptions;

/**
 * @author Bibibiu
 * created 8/28/19 at 10:02
 **/
public class TokenException extends RuntimeException{
    public TokenException(String message) {
        super(message);
    }

    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
