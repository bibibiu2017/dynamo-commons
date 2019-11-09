package ke.co.dynamodigital.commons.config.bean;

import ke.co.dynamodigital.commons.config.factories.Oauth2ClientCredentialsSupplierFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

/**
 * @author arthurmita
 * created 27/10/2019 at 19:21
 **/
@Configuration
@ConditionalOnClass(OAuth2RestTemplate.class)
public class Oauth2ClientCredentialsSupplierAutoConfigurer {

    @Bean
    @ConditionalOnMissingBean
    public Oauth2ClientCredentialsSupplierFactoryBean factoryBean() {
        return new Oauth2ClientCredentialsSupplierFactoryBean();
    }
}
