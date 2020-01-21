package ke.co.dynamodigital.commons.config.bean;

import ke.co.dynamodigital.commons.config.factories.AuthorizedClientFactoryBean;
import ke.co.dynamodigital.commons.services.AuthorizedClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author arthurmita
 * created 03/01/2020 at 13:50
 **/
@Configuration
@ConditionalOnClass(AuthorizedClient.class)
class AuthorizedClientAutoConfigurer {

    @Bean
    @ConditionalOnMissingBean(AuthorizedClientFactoryBean.class)
    AuthorizedClientFactoryBean authorizedClient() {
        return new AuthorizedClientFactoryBean();
    }
}
