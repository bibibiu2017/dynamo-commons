package ke.co.dynamodigital.commons.stream;

import ke.co.dynamodigital.commons.CommonsApplication;
import ke.co.dynamodigital.commons.config.TestProcessor;
import ke.co.dynamodigital.commons.config.annotations.MessageAdapterTest;
import ke.co.dynamodigital.commons.models.base.BaseResponse;
import ke.co.dynamodigital.commons.models.message.DelayedMessageModel;
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

import java.util.HashMap;
import java.util.function.Predicate;

import static ke.co.dynamodigital.commons.utils.AmqpUtils.RETURN_HEADER;


@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Slf4j
@MessageAdapterTest(classes = {CommonsApplication.class})
class MessageSenderTest {

    private SoftAssertions sofly;

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private OutputDestination outputDestination;

    private Message<String> message;

    @BeforeEach
    void setUp() {
        sofly = new SoftAssertions();
        message = AmqpUtils.buildMessageFrom("FOO");
    }

    @AfterEach
    void tearDown() {
        sofly.assertAll();
    }

    @Test
    void should_send_message_when_predicate_succeeds() {
        //GIVEN
        Predicate<Message<String>> send = message -> message.getPayload().equals(this.message.getPayload());

        //WHEN
        boolean sent = messageSender.send(message, TestProcessor.INPUT, send);
        Message<byte[]> message = outputDestination.receive(0, TestProcessor.OUTPUT);

        //THEN
        sofly.assertThat(sent).isTrue();
        sofly.assertThat(message).extracting(Message::getPayload).isEqualTo(this.message.getPayload().getBytes());
    }

    @Test
    void should_not_send_message_when_predicate_fails() {
        //GIVEN
        Predicate<Message<String>> send = message -> !message.getPayload().equals(this.message.getPayload());

        //WHEN
        boolean sent = messageSender.send(message, TestProcessor.INPUT, send);
        Message<byte[]> message = outputDestination.receive(0, TestProcessor.OUTPUT);

        //THEN
        sofly.assertThat(sent).isFalse();
        sofly.assertThat(message).isNull();
    }

    @Test
    void should_send_message_when_predicate_is_not_provided() {
        //GIVEN/WHEN
        boolean sent = messageSender.send(message, TestProcessor.INPUT);
        Message<byte[]> message = outputDestination.receive(0, TestProcessor.OUTPUT);

        //THEN
        sofly.assertThat(sent).isTrue();
        sofly.assertThat(message).extracting(Message::getPayload).isEqualTo(this.message.getPayload().getBytes());
    }

    @Test
    void should_send_message_with_object_payload() {
        //GIVEN/WHEN
        boolean sent = messageSender.send(message.getPayload(), TestProcessor.INPUT);
        Message<byte[]> message = outputDestination.receive(0, TestProcessor.OUTPUT);

        //THEN
        sofly.assertThat(sent).isTrue();
        sofly.assertThat(message).extracting(Message::getPayload).isEqualTo(this.message.getPayload().getBytes());
    }

    @Test
    void should_send_delayedMessage() {
        //GIVEN
        DelayedMessageModel<String> delayedMessage = DelayedMessageModel.<String>builder()
                .address(TestProcessor.INPUT)
                .delay(2000)
                .retires(1)
                .payload(message.getPayload())
                .build();

        //WHEN
        boolean sent = messageSender.send(delayedMessage);
        Message<byte[]> message = outputDestination.receive(0, TestProcessor.OUTPUT);
        //THEN
        sofly.assertThat(sent).isTrue();
        sofly.assertThat(message).extracting(Message::getHeaders)
                .asInstanceOf(InstanceOfAssertFactories.MAP)
                .containsEntry(AmqpUtils.RETRIES_HEADER, delayedMessage.getRetires() + 1)
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
        DelayedMessageModel<BaseResponse> delayedMessage = DelayedMessageModel.<BaseResponse>builder()
                .payload(BaseResponse.builder().message("FOO").build())
                .address(Processor.OUTPUT)
                .messageHeaders(headers)
                .delay(5000)
                .retires(2)
                .build();

        //when
        boolean sent = messageSender.send(delayedMessage);

        //then
        sofly.assertThat(sent).isTrue();
        sofly.assertThat(message)
                .extracting(Message::getHeaders)
                .asInstanceOf(InstanceOfAssertFactories.MAP)
                .doesNotContainEntry(AmqpUtils.DELAY_HEADER, 5000)
                .doesNotContainEntry(AmqpUtils.RETRIES_HEADER, 2)
                .doesNotContainEntry(AmqpUtils.DELAY_HEADER, 3000)
                .doesNotContainEntry(AmqpUtils.RETRIES_HEADER, 6);


        log.debug("\nMessageToDelay: {}", ObjectUtils.writeJson(delayedMessage));
    }
}
