package ke.co.dynamodigital.commons.services;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bibibiu
 * created 10/22/19 at 11:33
 **/
@Configuration
public class MessageSenderFactoryBean implements FactoryBean<MessageSender> {

    @Autowired
    private BinderAwareChannelResolver resolver;

    @Override
    public MessageSender getObject() throws Exception {
        if (resolver == null) {
            throw new Exception("Destination resolver cannot be null");
        }
        return MessageSenderImpl.builder().resolver(resolver).build();
    }

    @Override
    public Class<?> getObjectType() {
        return MessageSender.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
