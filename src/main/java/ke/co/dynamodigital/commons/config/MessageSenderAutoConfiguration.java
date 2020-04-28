package ke.co.dynamodigital.commons.config;

import ke.co.dynamodigital.commons.stream.MessageSender;
import ke.co.dynamodigital.commons.stream.MessageSenderFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;

/**
 * @author arthurmita
 * created 26/04/2020 at 01:13
 **/
@ConditionalOnClass(StreamBridge.class)
class MessageSenderAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean({MessageSender.class, MessageSenderFactoryBean.class})
    public MessageSenderFactoryBean messageSender() {
        return new MessageSenderFactoryBean();
    }
}
