package ke.co.dynamodigital.commons.error;

import ke.co.dynamodigital.commons.utils.ObjectUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author lawrence
 * This class centralizes our exception handling in that it interprets our {@link GenericHttpException}
 * checks the RESPONSE_CODE and gives useful info to the API user
 */
@Slf4j
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice //combines controllerAdvice & ResponseBody for easy bootstrapping with http
public class GlobalExceptionHandlerController {


    /**
     * on authentication fail inform user as clearly as possible - deny
     *
     * @param res Response Object
     * @throws IOException we are doing http so we expect something may go wrong
     */
    @ExceptionHandler(AccessDeniedException.class)
    public void handleAccessDeniedException(HttpServletResponse res) throws IOException {
        res.sendError(HttpStatus.FORBIDDEN.value(), "Access denied");
    }


    /**
     * if user input doesnt match our expected Data Transfer Objects then inform them
     * clearly too
     *
     * @param res Response Object
     * @param e   Exception thrown
     * @throws IOException we are doing http so we expect something may go wrong
     */
    @ExceptionHandler(Exception.class)
    public void handleException(HttpServletResponse res, Exception e) throws IOException {
        log.error("unknown exception", e);
        res.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid request data");
    }

    /**
     * sometimes errors are thrown and we dont know what went wrong
     * we will keep this during dev stage, not ideal for prod
     *
     * @param res Response Object
     * @param ex  Custom Exception handle
     */
    @SneakyThrows
    @ExceptionHandler(GenericHttpException.class)
    public void handleCustomException(HttpServletResponse res, GenericHttpException ex) {
        res.sendError(ex.getHttpStatus().value(), ex.getMessage());
    }


    /**
     * this method will clean up constraint violation errors and return an aggregated error string
     *
     * @param res Response object
     * @param ce  constraint violation exception
     */
    @SneakyThrows
    @ExceptionHandler(ConstraintViolationException.class)
    public void handleConstraintViolationException(HttpServletResponse res, ConstraintViolationException ce) {
        res.sendError(
                HttpStatus.BAD_REQUEST.value(),
                ce.getConstraintViolations().stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.joining(","))
        );
    }

    @SneakyThrows
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleValidationExceptions(HttpServletResponse res, MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        res.sendError(HttpStatus.BAD_REQUEST.value(), ObjectUtils.writeJson(errors));
    }

}
