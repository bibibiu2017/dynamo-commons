package ke.co.dynamodigital.commons.config;

import ke.co.dynamodigital.commons.utils.AmqpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static ke.co.dynamodigital.commons.utils.AmqpUtils.*;
import static ke.co.dynamodigital.commons.utils.ObjectUtils.writeJson;

/**
 * @author arthurmita
 * created 01/01/2020 at 03:53
 **/
@Slf4j
@TestConfiguration
@EnableAutoConfiguration
public class MessageTestConfiguration {
    @Bean
    public Function<Message<?>, Message<?>> testFunction() {
        return message -> {
            log.debug("\nReceived: {}", writeJson(message));
            Map<String, Object> headers = new HashMap<>() {{
                Integer retries = message.getHeaders().get(RETRIES_HEADER, Integer.class);
                put(DELAY_HEADER, 5000);
                put(RETURN_HEADER, "testProcessorInput");
                put(RETRIES_HEADER, retries == null ? 1 : retries);
            }};
            return AmqpUtils.buildMessageFrom(message.getPayload(), headers);
        };
    }
}
