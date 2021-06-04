package ke.co.dynamodigital.commons.utils;

import ke.co.dynamodigital.commons.error.GenericHttpException;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.springframework.data.util.Pair;
import org.springframework.messaging.*;

import java.util.*;

/**
 * @author arthurmita
 * created 02/07/2020 at 20:45
 **/
@UtilityClass
public class LogUtils {
    public String USER_SECURITY_KEY = "user_id";
    public String CLIENT_SECURITY_KEY = "client_id";

    /**
     * Helper that logs an error
     *
     * @param logger    logger
     * @param title     title of error
     * @param reason    reason the error occurred
     * @param exception Exception
     */
    public void logError(Logger logger, String title, String reason, Exception exception) {
        String message;
        if (exception instanceof GenericHttpException) {
            GenericHttpException e = (GenericHttpException) exception;
            message = "HttpStatus: " + e.getHttpStatus() + "\n\t" + e.getMessage();
        } else {
            message = reason;
        }
        if (exception.getCause() == null)
            logger.error("\n===============================================================" +
                    "\n     {}" +
                    "\nReason: {}" +
                    "\nException: {}" +
                    "\n===============================================================", title, message, exception.getClass().getSimpleName());
        else
            logger.error("\n===============================================================" +
                    "\n     {}" +
                    "\nReason: {}" +
                    "\nException: {}" +
                    "\nCause: {}" +
                    "\n===============================================================", title, message, exception.getClass().getSimpleName(), exception.getCause().getClass().getSimpleName());
    }

    /**
     * Helper that logs an error
     *
     * @param logger logger
     * @param title  title of error
     * @param reason reason the error occurred
     * @param clazz  Exception class
     */
    public void logError(Logger logger, String title, String reason, Class<? extends Exception> clazz) {
        String exception = clazz.getSimpleName();

        logger.error("\n===============================================================" +
                "\n     {}" +
                "\nReason: {}" +
                "\nException: {}" +
                "\n===============================================================", title, reason, exception);
    }


    /**
     * Helper that logs an object mapping event
     *
     * @param logger      logger
     * @param source      source object
     * @param destination destination object
     */
    public void logMapping(Logger logger, Object source, Object destination) {
        final String src = ObjectUtils.writeJson(source);
        final String des = ObjectUtils.writeJson(destination);
        final String srcName = source.getClass().getSimpleName();
        final String desName = destination.getClass().getSimpleName();

        logger.info("\n===========================================================" +
                "\nSource({})-->Destination({})" +
                "\n-----------------------------------------------------------" +
                "\n{}: {}" +
                "\n-----------------------------------------------------------" +
                "\n{}: {}" +
                "\n===========================================================", srcName, desName, srcName, src, desName, des);
    }

    public void logObject(Logger logger, Object object, String tittle) {
        if (Objects.isNull(object)) return;
        String json = object instanceof String ? (String) object : ObjectUtils.writeJson(object);
        logger.info("\n===========================================================" +
                "\n{}: {}" +
                "\n===========================================================", tittle, json);
    }

    public void logObject(Logger logger, Object object) {
        final String className = object.getClass().getSimpleName();
        logObject(logger, object, className);
    }

    public void logController(Logger logger, String title, String key, Object value) {
        var id = getClient().getFirst();
        var isUser = getClient().getSecond();

        logger.info("\n******************************************************" +
                "\n     {}" +
                "\n{}: {}" +
                "\n{}: {}" +
                "\n*******************************************************", title, key, ObjectUtils.writeJson(value), isUser ? "UserId" : "ClientId", id);
    }

    public void logController(Logger logger, String title, boolean request, Object value) {
        logController(logger, title, request ? "Request" : "Response", value);
    }

    public void logControllerRequest(Logger logger, String title, Object request) {
        logController(logger, title, true, request);
    }

    public void logControllerResponse(Logger logger, String title, Object response) {
        logController(logger, title, false, response);
    }

    public void logStream(Logger logger, Message<?> message, String input) {
        MessageHeaders messageHeaders = message.getHeaders();
        var tries = messageHeaders.get(AmqpUtils.RETRIES_HEADER) == null ? 1 : messageHeaders.get(AmqpUtils.RETRIES_HEADER);
        var death = messageHeaders.get(AmqpUtils.DEATH_HEADER);
        var delay = messageHeaders.get(AmqpUtils.AMQP_DELAY_HEADER);
        var payload = message.getPayload();
        var headers = new HashMap<>() {{
            put("Tries", tries);
            if (Objects.nonNull(death))
                put("Death", death);
            if (Objects.nonNull(delay))
                put("Delay", delay);
        }};
        if (logger.isDebugEnabled()) {
            logger.debug("\n******************************************************" +
                    "\n     Message" +
                    "\nStream: {}" +
                    "\nHeaders: {}" +
                    "\nPayload: {}" +
                    "\n*******************************************************", input, ObjectUtils.writeJson(headers), ObjectUtils.writeJson(payload));
        } else if (logger.isInfoEnabled()) {
            logger.info("\n******************************************************" +
                    "\n     Message" +
                    "\nStream: {}" +
                    "\nHeaders: {}" +
                    "\n*******************************************************", input, ObjectUtils.writeJson(headers));
        } else if (logger.isTraceEnabled()) {

            logger.trace("\n******************************************************" +
                    "\n     Message" +
                    "\nStream: {}" +
                    "\nHeaders: {}" +
                    "\nPayload: {}" +
                    "\n*******************************************************", input, ObjectUtils.writeJson(messageHeaders), ObjectUtils.writeJson(payload));
        }
    }

    private Pair<String, Boolean> getClient() {
        try {
            String userId = SecurityUtils.getExtraInfo(USER_SECURITY_KEY);
            if (Objects.nonNull(userId) && !userId.isBlank())
                return Pair.of(userId, true);
            else
                return Pair.of(SecurityUtils.getExtraInfo(CLIENT_SECURITY_KEY), false);
        } catch (Exception e) {
            return Pair.of("ANONYMOUS", false);
        }
    }
}
