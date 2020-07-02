package ke.co.dynamodigital.commons.utils;

import ke.co.dynamodigital.commons.error.GenericHttpException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author arthurmita
 * created 02/07/2020 at 20:45
 **/
@Slf4j
@UtilityClass
public class LogUtils {
    public String USER_SECURITY_KEY = "user_id";
    public String CLIENT_SECURITY_KEY = "client_id";

    /**
     * Helper that logs an error
     *
     * @param title     title of error
     * @param reason    reason the error occurred
     * @param exception exception thrown due to error
     */
    public void logError(String title, String reason, Exception exception) {
        String message;
        if (exception instanceof GenericHttpException) {
            GenericHttpException e = (GenericHttpException) exception;
            message = "HttpStatus: " + e.getHttpStatus() + "\n\t" + e.getMessage();
        } else {
            message = reason;
        }
        if (exception.getCause() == null)
            log.error("\n===============================================================" +
                    "\n     {}" +
                    "\nReason: {}" +
                    "\nException: {}" +
                    "\n===============================================================", title, message, exception.getClass().getSimpleName());
        else
            log.error("\n===============================================================" +
                    "\n     {}" +
                    "\nReason: {}" +
                    "\nException: {}" +
                    "\nCause: {}" +
                    "\n===============================================================", title, message, exception.getClass().getSimpleName(), exception.getCause().getClass().getSimpleName());
    }

    /**
     * Helper that logs an error
     *
     * @param title  title of error
     * @param reason reason the error occurred
     * @param clazz  Exception class
     */
    public void logError(String title, String reason, Class<? extends Exception> clazz) {
        String exception = clazz.getSimpleName();

        log.error("\n===============================================================" +
                "\n     {}" +
                "\nReason: {}" +
                "\nException: {}" +
                "\n===============================================================", title, reason, exception);
    }


    /**
     * Helper that logs an object mapping event
     *
     * @param source      source object
     * @param destination destination object
     */
    public void logMapping(Object source, Object destination) {
        final String src = ObjectUtils.writeJson(source);
        final String des = ObjectUtils.writeJson(destination);
        final String srcName = source.getClass().getSimpleName();
        final String desName = destination.getClass().getSimpleName();

        if (log.isInfoEnabled()) {
            log.info("\n===========================================================" +
                    "\nSource({})-->Destination({})" +
                    "\n-----------------------------------------------------------" +
                    "\n{}: {}" +
                    "\n-----------------------------------------------------------" +
                    "\n{}: {}" +
                    "\n===========================================================", srcName, desName, srcName, src, desName, des);
        } else if (log.isDebugEnabled()) {
            log.debug("\n===========================================================" +
                    "\nSource({})-->Destination({})" +
                    "\n-----------------------------------------------------------" +
                    "\n{}: {}" +
                    "\n-----------------------------------------------------------" +
                    "\n{}: {}" +
                    "\n===========================================================", srcName, desName, srcName, src, desName, des);
        } else if (log.isTraceEnabled()) {
            log.trace("\n===========================================================" +
                    "\nSource({})-->Destination({})" +
                    "\n-----------------------------------------------------------" +
                    "\n{}: {}" +
                    "\n-----------------------------------------------------------" +
                    "\n{}: {}" +
                    "\n===========================================================", srcName, desName, srcName, src, desName, des);
        }
    }

    public void logObject(Object object, String tittle) {
        if (Objects.isNull(object)) return;
        String json = object instanceof String ? (String) object : ObjectUtils.writeJson(object);
        if (log.isInfoEnabled()) {
            log.info("\n===========================================================" +
                    "\n{}: {}" +
                    "\n===========================================================", tittle, json);
        } else if (log.isDebugEnabled()) {
            log.debug("\n===========================================================" +
                    "\n{}: {}" +
                    "\n===========================================================", tittle, json);
        } else if (log.isTraceEnabled()) {
            log.trace("\n===========================================================" +
                    "\n{}: {}" +
                    "\n===========================================================", tittle, json);
        }
    }

    public void logObject(Object object) {
        final String className = object.getClass().getSimpleName();
        logObject(object, className);
    }

    public void logController(String title, boolean request, Object value) {
        String client = getClient();
        log.info("\n******************************************************" +
                "\n     {}" +
                "\n{}: {}" +
                "\nUserID: {}" +
                "\n*******************************************************", title, request ? "Request" : "Response", ObjectUtils.writeJson(value), client);
    }

    public void logController(String title, String key, Object value) {
        String client = getClient();
        log.info("\n******************************************************" +
                "\n     {}" +
                "\n{}: {}" +
                "\nUserID: {}" +
                "\n*******************************************************", title, key, ObjectUtils.writeJson(value), client);
    }

    public void logStream(Message<?> message, String input) {
        MessageHeaders messageHeaders = message.getHeaders();
        var headers = new HashMap<>() {{
            put("Death", messageHeaders.get(AmqpUtils.DEATH_HEADER));
            put("Tries", messageHeaders.get(AmqpUtils.RETRIES_HEADER));
            put("Delay", messageHeaders.get(AmqpUtils.AMQP_DELAY_HEADER));
        }};
        if (log.isDebugEnabled()) {
            log.debug("\n******************************************************" +
                    "\n     Message" +
                    "\ninput: {}" +
                    "\nheaders: {}" +
                    "\npayload: {}" +
                    "\n*******************************************************", input, ObjectUtils.writeJson(headers), ObjectUtils.writeJson(message.getPayload()));
        } else if (log.isInfoEnabled()) {
            log.info("\n******************************************************" +
                    "\n     Message" +
                    "\ninput: {}" +
                    "\nheaders: {}" +
                    "\n*******************************************************", input, ObjectUtils.writeJson(headers));
        } else if (log.isTraceEnabled()) {

            log.trace("\n******************************************************" +
                    "\n     Message" +
                    "\ninput: {}" +
                    "\nheaders: {}" +
                    "\npayload: {}" +
                    "\n*******************************************************", input, ObjectUtils.writeJson(headers), ObjectUtils.writeJson(message.getPayload()));
        }
    }

    private String getClient() {
        try {
            String userId = SecurityUtils.getExtraInfo(USER_SECURITY_KEY);
            return Objects.isNull(userId) ? SecurityUtils.getExtraInfo(CLIENT_SECURITY_KEY) : userId;
        } catch (Exception e) {
            return "ANONYMOUS";
        }
    }
}
