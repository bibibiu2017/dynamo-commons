package ke.co.dynamodigital.commons.services;

import ke.co.dynamodigital.commons.CommonsApplication;
import ke.co.dynamodigital.commons.annotations.TestProfile;
import ke.co.dynamodigital.commons.configs.test.TestProcessor;
import ke.co.dynamodigital.commons.utils.AmqpUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.cloud.stream.test.binder.MessageCollectorAutoConfiguration;
import org.springframework.messaging.Message;
import org.springframework.test.context.ContextConfiguration;

import java.util.concurrent.BlockingQueue;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.cloud.stream.test.matcher.MessageQueueMatcher.receivesPayloadThat;


@Slf4j
@TestProfile
@SpringBootTest
@ContextConfiguration(classes = {MessageCollectorAutoConfiguration.class, CommonsApplication.class})
class MessageSenderTest {

    @SpyBean
    private TestProcessor testProcessor;

    @Autowired
    private Processor processor;

    @Autowired
    private MessageCollector collector;

    @Autowired
    private MessageSender messageSender;

    private Message<String> message;

    @BeforeEach
    void setUp() {
        message = message = AmqpUtils.buildMessageFrom("FOO");
    }

    @Test
    @DisplayName("SendMessageWithPassingPredicate->MessageSentSuccessfully")
    void test1() {
        //when
        boolean sent = messageSender.send(message, Processor.INPUT, stringMessage ->
                stringMessage.getPayload().equals("FOO"));

        BlockingQueue<Message<?>> messages = collector.forChannel(processor.output());

        //then
        assertThat(messages, receivesPayloadThat(is("foo")));
        assertThat(sent,is(true));

        Mockito.verify(testProcessor).testProcessor(Mockito.anyString());
    }

    @Test
    @DisplayName("SendMessageWithFailingPredicate->MessageNotSent")
    void test2() {
        //when
        boolean sent = messageSender.send(message, Processor.INPUT, stringMessage ->
                stringMessage.getPayload().equals("BAR"));

        assertThat(sent,is(false));
        Mockito.verifyZeroInteractions(testProcessor);

    }

    @Test
    @DisplayName("SendMessage->MessageSentSuccessfully")
    void test3() {
        //when
        boolean sent = messageSender.send(message, Processor.INPUT);

        BlockingQueue<Message<?>> messages = collector.forChannel(processor.output());

        //then
        assertThat(messages, receivesPayloadThat(is("foo")));
        assertThat(sent,is(true));

        Mockito.verify(testProcessor).testProcessor(Mockito.anyString());
    }
}
