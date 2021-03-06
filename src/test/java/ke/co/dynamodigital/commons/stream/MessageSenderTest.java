package ke.co.dynamodigital.commons.stream;

import ke.co.dynamodigital.commons.CommonsApplication;
import ke.co.dynamodigital.commons.config.TestProcessor;
import ke.co.dynamodigital.commons.config.annotations.MessageAdapterTest;
import ke.co.dynamodigital.commons.models.message.DelayedMessage;
import ke.co.dynamodigital.commons.utils.AmqpUtils;
import ke.co.dynamodigital.commons.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import static ke.co.dynamodigital.commons.utils.AmqpUtils.RETURN_HEADER;


@Slf4j
@ActiveProfiles("test_commons")
@MessageAdapterTest(classes = {CommonsApplication.class})
class MessageSenderTest {

    private SoftAssertions softly;

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private OutputDestination outputDestination;

    private Message<String> message;

    @BeforeEach
    void setUp() {
        softly = new SoftAssertions();
        message = AmqpUtils.buildMessageFrom("FOO");
    }

    @AfterEach
    void tearDown() {
        softly.assertAll();
    }

    @Test
    void should_send_message_when_predicate_succeeds() {
        //GIVEN
        Predicate<Message<String>> send = message -> message.getPayload().equals(this.message.getPayload());

        //WHEN
        boolean sent = messageSender.send(message, TestProcessor.INPUT, send);
        Message<byte[]> message = outputDestination.receive(0, TestProcessor.OUTPUT);

        //THEN
        softly.assertThat(sent).isTrue();
        softly.assertThat(message).extracting(Message::getPayload).isEqualTo(this.message.getPayload().getBytes());
    }

    @Test
    void should_not_send_message_when_predicate_fails() {
        //GIVEN
        Predicate<Message<String>> send = message -> !message.getPayload().equals(this.message.getPayload());

        //WHEN
        boolean sent = messageSender.send(message, TestProcessor.INPUT, send);
        Message<byte[]> message = outputDestination.receive(0, TestProcessor.OUTPUT);

        //THEN
        softly.assertThat(sent).isFalse();
        softly.assertThat(message).isNull();
    }

    @Test
    void should_send_message_when_predicate_is_not_provided() {
        //GIVEN/WHEN
        boolean sent = messageSender.send(message, TestProcessor.INPUT);
        Message<byte[]> message = outputDestination.receive(0, TestProcessor.OUTPUT);

        //THEN
        softly.assertThat(sent).isTrue();
        softly.assertThat(message).extracting(Message::getPayload).isEqualTo(this.message.getPayload().getBytes());
    }

    @Test
    void should_send_message_with_object_payload() {
        //GIVEN/WHEN
        boolean sent = messageSender.send(message.getPayload(), TestProcessor.INPUT);
        Message<byte[]> message = outputDestination.receive(0, TestProcessor.OUTPUT);

        //THEN
        softly.assertThat(sent).isTrue();
        softly.assertThat(message).extracting(Message::getPayload).isEqualTo(this.message.getPayload().getBytes());
    }

    @Test
    void should_send_delayedMessage() {
        //GIVEN
        DelayedMessage<String> delayedMessage = DelayedMessage.<String>builder()
                .address(TestProcessor.INPUT)
                .delay(2000)
                .retires(1)
                .payload(message.getPayload())
                .build();

        //WHEN
        boolean sent = messageSender.send(delayedMessage);
        Message<byte[]> message = outputDestination.receive(0, ParkingStream.OUTPUT);

        //THEN
        softly.assertThat(sent).isTrue();
        softly.assertThat(message).extracting(Message::getHeaders)
                .asInstanceOf(InstanceOfAssertFactories.MAP)
                .containsEntry(AmqpUtils.RETRIES_HEADER, delayedMessage.getRetires())
                .containsEntry(RETURN_HEADER, delayedMessage.getAddress())
                .containsEntry(AmqpUtils.DELAY_HEADER, delayedMessage.getDelay());
    }


    @Test
    void should_send_delayed_message_with_message_headers_taking_preference() {
        //given
        val headers = new MessageHeaders(new HashMap<>() {{
            put(AmqpUtils.DELAY_HEADER, 3000);
            put(AmqpUtils.RETRIES_HEADER, 6);
            put(RETURN_HEADER, Processor.INPUT);
        }});
        DelayedMessage<Map<String, String>> delayedMessage = DelayedMessage.<Map<String, String>>builder()
                .payload(Map.of("message","FOO"))
                .address(Processor.OUTPUT)
                .messageHeaders(headers)
                .delay(5000)
                .retires(2)
                .build();

        //when
        boolean sent = messageSender.send(delayedMessage);
        Message<byte[]> message = outputDestination.receive(0, ParkingStream.OUTPUT);

        //then
        softly.assertThat(sent).isTrue();
        softly.assertThat(message)
                .extracting(Message::getHeaders)
                .asInstanceOf(InstanceOfAssertFactories.MAP)
                .doesNotContainEntry(AmqpUtils.DELAY_HEADER, 5000)
                .doesNotContainEntry(AmqpUtils.RETRIES_HEADER, 2)
                .containsEntry(AmqpUtils.DELAY_HEADER, 3000)
                .containsEntry(AmqpUtils.RETRIES_HEADER, 6);


        log.debug("\nMessageToDelay: {}", ObjectUtils.writeJson(delayedMessage));
    }
}
