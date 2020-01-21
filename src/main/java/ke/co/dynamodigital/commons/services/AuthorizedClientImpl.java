package ke.co.dynamodigital.commons.services;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

/**
 * @author arthurmita
 * created 14/12/2019 at 22:02
 **/
@RequiredArgsConstructor
public class AuthorizedClientImpl implements AuthorizedClient {

    final private AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager;

    @Override
    public OAuth2AuthorizedClient authorize(String registeredClient) {
        val authorizedRequest = OAuth2AuthorizeRequest.withClientRegistrationId(registeredClient)
                .principal(new AnonymousAuthenticationToken(
                        registeredClient,registeredClient, AuthorityUtils.createAuthorityList("ANONYMOUS")))
                .build();
        return authorizedClientManager.authorize(authorizedRequest);
    }
}
