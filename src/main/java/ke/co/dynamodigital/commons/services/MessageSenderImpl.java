package ke.co.dynamodigital.commons.services;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.messaging.Message;

import java.util.function.Predicate;

/**
 * @author Bibibiu
 * created 10/22/19 at 11:42
 **/
@Data
@Slf4j
@SuperBuilder(toBuilder = true)
public class MessageSenderImpl implements MessageSender {

    private BinderAwareChannelResolver resolver;

    @Override
    public <T> boolean send(Message<T> message, String destination, Predicate<Message<T>> send) {
        if (send.test(message)) {
            log.info("\n===================================\nsend condition passed will send message" +
                    "\n===================================");
            return resolver.resolveDestination(destination).send(message);
        }
        log.info("\n====================================\nsend condition failed will not send message" +
                "\n====================================");
        return false;
    }

    @Override
    public <T> boolean send(Message<T> message, String destination) {

        return send(message, destination, tMessage -> true);
    }
}
