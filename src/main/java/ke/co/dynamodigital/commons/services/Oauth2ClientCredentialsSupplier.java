package ke.co.dynamodigital.commons.services;

import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

/**
 * @author arthurmita
 * created 27/10/2019 at 20:20
 **/
@FunctionalInterface
public interface Oauth2ClientCredentialsSupplier {
    ClientCredentialsResourceDetails getCredentials();
}
