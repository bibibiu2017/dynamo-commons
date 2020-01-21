package ke.co.dynamodigital.commons.config.factories;

import ke.co.dynamodigital.commons.services.AuthorizedClient;
import ke.co.dynamodigital.commons.services.AuthorizedClientImpl;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;

/**
 * @author arthurmita
 * created 03/01/2020 at 13:48
 **/
public class AuthorizedClientFactoryBean implements FactoryBean<AuthorizedClient> {

    @Autowired(required = false)
    private AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager;

    @Override
    public AuthorizedClient getObject() throws Exception {
        if (authorizedClientManager == null) {
            throw new Exception("A bean of Class ["+
                    AuthorizedClientServiceOAuth2AuthorizedClientManager.class.getSimpleName()
                    +"] is required in class path");
        }
        return new AuthorizedClientImpl(authorizedClientManager);
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
