package ke.co.dynamodigital.commons.configs.bean;

import ke.co.dynamodigital.commons.services.MessageSender;
import ke.co.dynamodigital.commons.services.MessageSenderFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bibibiu
 * created 10/22/19 at 16:25
 **/
@Configuration
@ConditionalOnClass(MessageSender.class)
public class MessageSenderAutoConfigurer {

    @Bean
    @ConditionalOnMissingBean
    public MessageSenderFactoryBean messageSenderFactoryBean() {
        return new MessageSenderFactoryBean();
    }
}
