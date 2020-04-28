package ke.co.dynamodigital.commons.stream;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;

/**
 * @author arthurmita
 * created 26/04/2020 at 01:07
 **/
public class MessageSenderFactoryBean implements FactoryBean<MessageSenderImpl> {

    private StreamBridge streamBridge;

    @Override
    public MessageSenderImpl getObject() throws Exception {
        if (streamBridge == null) {
            throw new Exception("Instance of: " + StreamBridge.class.getName() + " is required");
        }
        return new MessageSenderImpl(streamBridge);
    }

    @Override
    public Class<?> getObjectType() {
        return MessageSender.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Autowired(required = false)
    public void setStreamBridge(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }
}
