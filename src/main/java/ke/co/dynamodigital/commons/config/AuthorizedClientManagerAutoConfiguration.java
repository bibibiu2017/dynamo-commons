package ke.co.dynamodigital.commons.config;

import ke.co.dynamodigital.commons.security.AuthorizedClientManagerFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;

/**
 * @author arthurmita
 * created 26/04/2020 at 01:24
 **/
@Configuration
@ConditionalOnClass(AuthorizedClientServiceOAuth2AuthorizedClientManager.class)
class AuthorizedClientManagerAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AuthorizedClientManagerFactoryBean authorizedClientManagerService() {
        return new AuthorizedClientManagerFactoryBean();
    }
}
