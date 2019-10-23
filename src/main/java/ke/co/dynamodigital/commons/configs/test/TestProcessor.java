package ke.co.dynamodigital.commons.configs.test;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;

/**
 * @author Bibibiu
 * created 10/22/19 at 19:56
 **/
@EnableBinding(Processor.class)
public class TestProcessor {

    @Transformer(inputChannel = Processor.INPUT,outputChannel = Processor.OUTPUT)
    public String testProcessor(String payload) {
        return payload.toLowerCase();
    }
}
