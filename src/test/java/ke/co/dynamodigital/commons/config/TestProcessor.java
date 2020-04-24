package ke.co.dynamodigital.commons.config;

import ke.co.dynamodigital.commons.utils.AmqpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Function;

import static ke.co.dynamodigital.commons.utils.ObjectUtils.writeJson;

/**
 * @author arthurmita
 * created 01/01/2020 at 03:53
 **/
@Slf4j
@Configuration
public class TestProcessor {

    public static final String INPUT = "testFunction-in-0";
    public static final String OUTPUT = "testFunction-out-0";

    @Bean
    public Function<Message<?>, Message<?>> testFunction() {
        return message -> {
            log.debug("\n=====================================" +
                    "\nReceived: {}" +
                    "\n======================================", writeJson(message));
            return AmqpUtils.buildMessageFrom(message.getPayload(), message.getHeaders());
        };
    }
}
