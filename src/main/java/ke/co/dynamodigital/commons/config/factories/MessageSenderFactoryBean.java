package ke.co.dynamodigital.commons.config.factories;

import ke.co.dynamodigital.commons.services.MessageSender;
import ke.co.dynamodigital.commons.services.MessageSenderImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bibibiu
 * created 10/22/19 at 11:33
 **/
@Slf4j
@Configuration
public class MessageSenderFactoryBean implements FactoryBean<MessageSender> {

    @Autowired(required = false)
    private BinderAwareChannelResolver resolver;

    @Override
    public MessageSender getObject() throws Exception {
        if (resolver == null) {
            throw new Exception("Could not find a bean for " + BinderAwareChannelResolver.class.getName() +
                    " please make sure it is autowired");
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
