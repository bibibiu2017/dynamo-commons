package ke.co.dynamodigital.commons.security;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;

/**
 * @author arthurmita
 * created 26/04/2020 at 01:20
 **/
public class AuthorizedClientManagerFactoryBean implements FactoryBean<AuthorizedClientManagerService> {

    private AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager;

    @Override
    public AuthorizedClientManagerService getObject() throws Exception {
        if (authorizedClientManager == null)
            throw new Exception("Bean of type: " +
                    AuthorizedClientServiceOAuth2AuthorizedClientManager.class.getName() +
                    " is required");
        return new AuthorizedClientManagerService(authorizedClientManager);
    }

    @Override
    public Class<?> getObjectType() {
        return AuthorizedClientManager.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Autowired(required = false)
    public void setAuthorizedClientManager(AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager) {
        this.authorizedClientManager = authorizedClientManager;
    }
}
