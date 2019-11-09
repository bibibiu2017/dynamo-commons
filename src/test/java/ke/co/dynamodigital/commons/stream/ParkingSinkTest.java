package ke.co.dynamodigital.commons.stream;

import ke.co.dynamodigital.commons.CommonsApplication;
import ke.co.dynamodigital.commons.models.accounts.CashWalletModel;
import ke.co.dynamodigital.commons.services.MessageSender;
import ke.co.dynamodigital.commons.stream.parking.ParkingSink;
import ke.co.dynamodigital.commons.utils.AmqpUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.cloud.stream.test.binder.MessageCollectorAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import static ke.co.dynamodigital.commons.utils.ObjectUtils.writeJson;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;

/**
 * @author arthurmita
 * created 09/11/2019 at 16:35
 **/
@Slf4j
@SpringBootTest
@ContextConfiguration
@ActiveProfiles("test")
class ParkingSinkTest {

    @Configuration
    @ComponentScan(basePackageClasses = {MessageCollectorAutoConfiguration.class, CommonsApplication.class})
    public static class Context {
    }

    @Autowired
    private Processor processor;

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private MessageCollector messageCollector;

    private static Message<?> message;
    private static final CashWalletModel wallet = CashWalletModel.builder()
            .availableBalance(BigDecimal.TEN)
            .actualBalance(BigDecimal.TEN)
            .build();
    private static final Integer RETRIES = 0;
    private static final Integer DELAY = 2000;

    @BeforeAll
    static void setUpAll() {
        Map<String, Object> headers = new HashMap<>();
        headers.put(AmqpUtils.RETRIES_HEADER, RETRIES);
        headers.put(AmqpUtils.DELAY_HEADER, DELAY);
        headers.put(AmqpUtils.RETURN_HEADER, Source.OUTPUT);
        message = AmqpUtils.buildMessageFrom(writeJson(wallet), headers);
    }

    @Test
    @SneakyThrows
    @DisplayName("Message-->Parking--->Returned")
    void sendToParkingExchange() {
        //when
        messageSender.send(message, ParkingSink.INPUT);
        BlockingQueue<Message<?>> messages = messageCollector.forChannel(processor.output());

        Message<?> message = messages.poll();
        assertThat(message.getHeaders(), hasEntry(AmqpUtils.RETRIES_HEADER, RETRIES + 1));
        log.debug("\nParkedMessage: {}",writeJson(message));
    }
}
