package ke.co.dynamodigital.commons.config.factories;

import ke.co.dynamodigital.commons.services.Oauth2ClientCredentialsSupplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

import static ke.co.dynamodigital.commons.utils.ObjectUtils.writeJson;

/**
 * @author arthurmita
 * created 27/10/2019 at 21:50
 **/
@Slf4j
public class Oauth2RestTemplateFactoryBean implements FactoryBean<OAuth2RestTemplate> {

    @Autowired(required = false)
    private Oauth2ClientCredentialsSupplier credentialsSupplier;

    @Override
    public OAuth2RestTemplate getObject() throws Exception {
        if (credentialsSupplier == null) {
            throw new Exception("Could not create rest template cred supplier not provided");
        }
        log.info("\n======================================================================" +
                "\nConfiguring oauth2 rest template with credential---->" +
                "\n{}" +
                "\n======================================================================"
                ,writeJson(credentialsSupplier.getCredentials()));
        return new OAuth2RestTemplate(credentialsSupplier.getCredentials());
    }

    @Override
    public Class<?> getObjectType() {
        return OAuth2RestTemplate.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
