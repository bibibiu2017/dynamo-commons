package ke.co.dynamodigital.commons.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;

/**
 * @author Bibibiu
 * created 10/22/19 at 19:56
 **/
@EnableBinding({
        Processor.class
})
public class TestProcessor {

    @Transformer(inputChannel = Processor.INPUT,outputChannel = Processor.OUTPUT)
    public Object testProcessor(Message<Object> message) {

        if (message.getPayload() instanceof String)
            return ((String) message.getPayload()).toLowerCase();
        else
            return message.getPayload();
    }
}
