package ke.co.dynamodigital.commons.configs.notifications;

import ke.co.dynamodigital.commons.services.NotificationsBuilderImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bibibiu
 * created 10/20/19 at 07:31
 **/
@Configuration
@ConditionalOnClass(NotificationsBuilderImpl.class)
public class NotificationsBuilderAutoConfigurer {

    @Bean
    @ConditionalOnMissingBean(NotificationSBuilderFactoryBean.class)
    public NotificationSBuilderFactoryBean NotificationSBuilderFactoryBean() {
        return new NotificationSBuilderFactoryBean();
    }
}
