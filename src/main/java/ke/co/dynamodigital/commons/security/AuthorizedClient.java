package ke.co.dynamodigital.commons.security;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

/**
 * @author arthurmita
 * created 14/12/2019 at 22:00
 **/
@FunctionalInterface
public interface AuthorizedClient {
    OAuth2AuthorizedClient authorize(String registeredClient);
}
