package ke.co.dynamodigital.commons.models.swagger;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

/**
 * @author arthurmita
 * created 05/06/2021 at 03:07
 **/
@Data
@AllArgsConstructor(staticName = "of")
public class Error {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public static Error badRequest() {
        LocalDateTime now = LocalDateTime.now();
        int status = BAD_REQUEST.value();
        String error = BAD_REQUEST.getReasonPhrase();
        String message = "Invalid request data";
        String path = "/path/to/request";
        return Error.of(now, status, error, message, path);
    }

    public static Error forbidden() {
        LocalDateTime now = LocalDateTime.now();
        int status = FORBIDDEN.value();
        String error = FORBIDDEN.getReasonPhrase();
        String message = "Access Denied";
        String path = "/path/to/request";
        return Error.of(now, status, error, message, path);
    }

    public static Error conflict() {
        LocalDateTime now = LocalDateTime.now();
        int status = CONFLICT.value();
        String error = CONFLICT.getReasonPhrase();
        String message = "Conflict error message";
        String path = "/path/to/request";
        return Error.of(now, status, error, message, path);
    }

    public static Error unprocessableEntity() {
        LocalDateTime now = LocalDateTime.now();
        int status = UNPROCESSABLE_ENTITY.value();
        String error = UNPROCESSABLE_ENTITY.getReasonPhrase();
        String message = "Unprocessable Entity error message";
        String path = "/path/to/request";
        return Error.of(now, status, error, message, path);
    }

}