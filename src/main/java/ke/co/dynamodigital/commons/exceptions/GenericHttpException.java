package ke.co.dynamodigital.commons.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author arthurmita
 * created 21/02/2020 at 11:09
 **/
@Getter
public class GenericHttpException extends RuntimeException {
    private HttpStatus httpStatus;

    public GenericHttpException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.httpStatus = status;
    }

    public GenericHttpException(String message, HttpStatus status) {
        super(message);
        this.httpStatus = status;
    }

    public GenericHttpException(HttpStatus status) {
        super();
        this.httpStatus = status;
    }
}
