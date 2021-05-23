package ke.co.dynamodigital.commons.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import ke.co.dynamodigital.commons.utils.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static org.springframework.http.HttpStatus.BAD_REQUEST;


/**
 * @author lawrence
 * This class centralizes our exception handling in that it interprets our {@link GenericHttpException}
 * checks the RESPONSE_CODE and gives useful info to the API user
 */
@Slf4j
@Configuration
@RestControllerAdvice //combines controllerAdvice & ResponseBody for easy bootstrapping with http
public class GlobalExceptionHandlerController {


    /**
     * on authentication fail inform user as clearly as possible - deny
     * @return error
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Error> handleAccessDeniedException() {
        return Error.response("Access Denied", HttpStatus.FORBIDDEN);
    }


    /**
     * if user input doesnt match our expected Data Transfer Objects then inform them
     * clearly too
     *
     * @param e   Exception thrown
     * @return error
     * @throws IOException we are doing http so we expect something may go wrong
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception e) throws IOException {
        log.error("unknown exception", e);
        return Error.response("Invalid request data", BAD_REQUEST);
    }

    /**
     * sometimes errors are thrown and we dont know what went wrong
     * we will keep this during dev stage, not ideal for prod
     *
     * @param res Response Object
     * @param ex  Custom Exception handle
     * @return error
     */
    @SneakyThrows
    @ExceptionHandler(GenericHttpException.class)
    public ResponseEntity<Error> handleCustomException(HttpServletResponse res, GenericHttpException ex) {
        return Error.response(ex.getMessage(), ex.getHttpStatus());
    }


    /**
     * this method will clean up constraint violation errors and return an aggregated error string
     *
     * @param ce  constraint violation exception
     * @return response
     */
    @SneakyThrows
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Error> handleConstraintViolationException(ConstraintViolationException ce) {
        String message = ce.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));
        return Error.response(message, BAD_REQUEST);
    }

    @SneakyThrows
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return Error.response(ObjectUtils.writeJson(errors), BAD_REQUEST);
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder(toBuilder = true)
    public static class Error {
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Africa/Nairobi", shape = STRING)
        private LocalDateTime timestamp;
        private Integer status;
        private String error;
        private String message;

        public static Error getInstance(String message, HttpStatus status) {
            return Error.builder()
                    .error(status.getReasonPhrase())
                    .status(status.value())
                    .timestamp(ObjectUtils.nowEAT())
                    .message(message)
                    .build();
        }

        public static ResponseEntity<Error> response(String message, HttpStatus status) {
            var response = getInstance(message, status);
            LogUtils.logController(log, "Error Response", false, response);
            return ResponseEntity.status(status).body(response);
        }
    }
}
