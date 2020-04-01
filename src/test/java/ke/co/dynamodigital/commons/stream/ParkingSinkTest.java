package ke.co.dynamodigital.commons.stream;

import ke.co.dynamodigital.commons.config.MessageTestConfiguration;
import ke.co.dynamodigital.commons.utils.AmqpUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.test.context.ActiveProfiles;

import static ke.co.dynamodigital.commons.utils.ObjectUtils.readJson;
import static ke.co.dynamodigital.commons.utils.ObjectUtils.writeJson;

/**
 * @author arthurmita
 * created 09/11/2019 at 16:35
 **/
@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class ParkingSinkTest {

    @Test
    void configurationTest() {
        try (ConfigurableApplicationContext context = new SpringApplicationBuilder(TestChannelBinderConfiguration
                .getCompleteConfiguration(MessageTestConfiguration.class))
                .run(
                        "--spring.cloud.stream.default.contentType=application/json",
                        "--spring.cloud.function.definition=testFunction"
                )
        ) {
            InputDestination inputDestination = context.getBean(InputDestination.class);
            OutputDestination outputDestination = context.getBean(OutputDestination.class);

            inputDestination.send(AmqpUtils.buildMessageFrom("{\"HI\" : \"This is a test\"}"), 0);
            Message<byte[]> message = outputDestination.receive(0, 0);
            log.debug("\nMessage: {}", writeJson(message));
            log.debug("\nPayload: {}", writeJson(readJson(new String(message.getPayload()), Object.class)));
        }
    }
}
