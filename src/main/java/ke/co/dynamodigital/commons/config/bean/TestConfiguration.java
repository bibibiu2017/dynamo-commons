package ke.co.dynamodigital.commons.config.bean;

import ke.co.dynamodigital.commons.utils.AmqpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@Configuration
@EnableAutoConfiguration
public class TestConfiguration {
    @Bean
    public Function<Message<?>, Message<?>> testFunction() {
        return message -> {
            log.debug("\nReceived: {}", writeJson(message));
            Map<String,Object> headers = new HashMap<String, Object>() {{
                Integer retries = message.getHeaders().get(RETRIES_HEADER,Integer.class);
                put(DELAY_HEADER,5000);
                put(RETURN_HEADER,"testProcessorInput");
                put(RETRIES_HEADER, retries == null ? 1 : retries);
            }};
            return AmqpUtils.buildMessageFrom(message.getPayload(),headers);
        };
    }
}
