package ke.co.dynamodigital.commons.config.factories;

import ke.co.dynamodigital.commons.services.Oauth2ClientCredentialsSupplier;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

/**
 * @author arthurmita
 * created 27/10/2019 at 21:33
 **/
public class Oauth2ClientCredentialsSupplierFactoryBean implements FactoryBean<Oauth2ClientCredentialsSupplier> {

    @Value("${pawa.oauth2.configuration.clientId:myClientId}")
    private String clientId;

    @Value("${pawa.oauth2.configuration.clientSecret:mySecret}")
    private String clientSecret;

    @Value("${pawa.oauth2.configuration.tokenUri:localhost}")
    private String tokenUri;

    @Override
    public Oauth2ClientCredentialsSupplier getObject() throws Exception {
        final ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        resourceDetails.setClientId(clientId);
        resourceDetails.setClientSecret(clientSecret);
        resourceDetails.setAccessTokenUri(tokenUri);
        return () -> resourceDetails;
    }

    @Override
    public Class<?> getObjectType() {
        return Oauth2ClientCredentialsSupplier.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
