package ke.co.dynamodigital.commons.configs.bean;

import ke.co.dynamodigital.commons.configs.factories.Oauth2RestTemplateFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

/**
 * @author arthurmita
 * created 27/10/2019 at 22:06
 **/
@Configuration
@ConditionalOnClass(OAuth2RestTemplate.class)
public class Oauth2RestTemplateAutoConfigurer {

    @Bean
    @ConditionalOnMissingBean(OAuth2RestTemplate.class)
    public Oauth2RestTemplateFactoryBean oauth2RestTemplateFactoryBean() {
        return new Oauth2RestTemplateFactoryBean();
    }
}
