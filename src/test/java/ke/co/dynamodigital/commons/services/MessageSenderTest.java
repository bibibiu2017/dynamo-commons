package ke.co.dynamodigital.commons.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@Slf4j
@SpringBootTest
@ActiveProfiles("test")
//@ContextConfiguration(classes = {MessageCollectorAutoConfiguration.class, CommonsApplication.class})
class MessageSenderTest {

    /*@SpyBean
    private TestProcessor testProcessor;

    @Autowired
    private Processor processor;

    @Autowired
    private ParkingSource parkingSource;

    @Autowired
    private MessageCollector collector;

    @Autowired
    private MessageSender messageSender;

    private static Message<String> message;

    private static DelayedMessageDTO<BaseResponse> delayedMessage;

    @BeforeAll
    static void setUpAll() {
        message = AmqpUtils.buildMessageFrom("FOO");
        delayedMessage = DelayedMessageDTO.<BaseResponse>builder()
                .address(Processor.INPUT)
                .payload(BaseResponse.builder()
                        .message("DELAY MESSAGE SENDER TEST")
                        .timestamp(nowEAT())
                        .build())
                .build();
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
        assertThat(sent, is(true));

        Mockito.verify(testProcessor).testProcessor(Mockito.any());
    }

    @Test
    @DisplayName("SendMessageWithFailingPredicate->MessageNotSent")
    void test2() {
        //when
        boolean sent = messageSender.send(message, Processor.INPUT, stringMessage ->
                stringMessage.getPayload().equals("BAR"));

        assertThat(sent, is(false));
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
        assertThat(sent, is(true));

        Mockito.verify(testProcessor).testProcessor(Mockito.any());
    }

    @Test
    @DisplayName("SendMessagePayload-->MessageSentSuccessfully")
    void test4() {
        //when
        boolean sent = messageSender.send(message.getPayload(), Processor.INPUT);

        BlockingQueue<Message<?>> messages = collector.forChannel(processor.output());

        //then
        assertThat(messages, receivesPayloadThat(is("foo")));
        assertThat(sent, is(true));

        Mockito.verify(testProcessor).testProcessor(Mockito.any());
    }

    @Test
    @DisplayName("SendDelayedMessage--->MessageSentSuccessfully")
    void test5() {
        //when
        boolean sent = messageSender.sendDelayed(delayedMessage);
        BlockingQueue<Message<?>> messages = collector.forChannel(parkingSource.sourceOutput());

        Message<?> message = messages.poll();

        //then
        assertThat(sent,is(true));
        assertThat(message, is(notNullValue(Message.class)));
        assertThat(message.getHeaders(), Matchers.allOf(
                Matchers.hasEntry(DELAY_HEADER, 2000),
                Matchers.hasEntry(RETRIES_HEADER, 1)
        ));
        assertThat(message.getHeaders(), hasEntry(RETURN_HEADER, delayedMessage.getAddress()));

        log.debug("\nMessageToDelay: {}",writeJson(message));
    }

    @Test
    @DisplayName("SendDelayedMessage--->MessageHeadersTakePrecedence")
    void test6() {
        //given
        val headers = new MessageHeaders(new HashMap<String, Object>(){{
            put(DELAY_HEADER,3000);
            put(RETRIES_HEADER, 6);
            put(RETURN_HEADER, Processor.INPUT);
        }});
        DelayedMessageDTO<BaseResponse> delayedMessageDTO = delayedMessage.toBuilder()
                .address(Processor.OUTPUT)
                .delay(5000)
                .retires(2)
                .messageHeaders(headers)
                .build();

        //when
        boolean sent = messageSender.sendDelayed(delayedMessageDTO);
        BlockingQueue<Message<?>> messages = collector.forChannel(parkingSource.sourceOutput());
        Message<?> message = messages.poll();

        //then
        assertThat(sent,is(true));
        assertThat(message, is(notNullValue(Message.class)));
        assertThat(message.getHeaders(), Matchers.allOf(
                Matchers.hasEntry(DELAY_HEADER, 3000),
                Matchers.hasEntry(RETRIES_HEADER, 6)
        ));
        assertThat(message.getHeaders(), hasEntry(RETURN_HEADER, Processor.INPUT));

        log.debug("\nMessageToDelay: {}",writeJson(message));
    }*/
}
