package ke.co.dynamodigital.commons.config.bean;

import ke.co.dynamodigital.commons.services.NotificationsBuilder;
import ke.co.dynamodigital.commons.config.factories.NotificationsBuilderFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bibibiu
 * created 10/20/19 at 07:31
 **/
@Configuration
@ConditionalOnClass(NotificationsBuilder.class)
public class NotificationsBuilderAutoConfigurer {

    @Bean
    @ConditionalOnMissingBean(NotificationsBuilderFactoryBean.class)
    public NotificationsBuilderFactoryBean notificationsBuilder() {
        return new NotificationsBuilderFactoryBean();
    }
}
