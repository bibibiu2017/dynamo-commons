package ke.co.dynamodigital.commons.stream;

import ke.co.dynamodigital.commons.config.annotations.MessageAdapterTest;
import ke.co.dynamodigital.commons.config.extension.WithSoftAssertions;
import ke.co.dynamodigital.commons.models.message.DelayedMessage;
import ke.co.dynamodigital.commons.utils.AmqpUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.test.*;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@MessageAdapterTest(profiles = {"test_commons"})
class ParkingStreamTest extends WithSoftAssertions {

    @Autowired
    MessageSender messageSender;

    @Autowired
    InputDestination inputDestination;

    @Autowired
    OutputDestination outputDestination;

    
    @Test
    @DisplayName("should consume delayed message publish to address and increment tries")
    void shouldConsumeDelayedMessagePublishToAddressAndIncrementTries() {
        //given:
        var retires = 1;
        var delay = 15000;
        var payload = "FOO";
        var address = "test-out-0";
        messageSender.send(DelayedMessage.from(payload, address, retires, delay));
        var message = outputDestination.receive(0, ParkingStream.OUTPUT);

        //when:
        inputDestination.send(message, ParkingStream.INPUT);

        //then:
        var delayedMessage = outputDestination.receive(0, address);
        softly.assertThat(delayedMessage).isNotNull()
                .satisfies(msg -> {
                    softly.assertThat(msg.getHeaders())
                            .containsEntry(AmqpUtils.RETURN_HEADER, address)
                            .containsEntry(AmqpUtils.RETRIES_HEADER, retires + 1)
                            .containsEntry(AmqpUtils.DELAY_HEADER, delay);

                });
    }
}