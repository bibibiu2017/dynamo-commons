package ke.co.dynamodigital.commons.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

/**
 * @author arthurmita
 * created 26/04/2020 at 01:18
 **/
@Slf4j
@RequiredArgsConstructor
public class AuthorizedClientManagerService implements AuthorizedClientManager {

    private final AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager;

    @Override
    public OAuth2AuthorizedClient authorize(String registeredClient) {
        val authorizedRequest = OAuth2AuthorizeRequest.withClientRegistrationId(registeredClient)
                .principal(new AnonymousAuthenticationToken(
                        registeredClient, registeredClient, AuthorityUtils.createAuthorityList("ANONYMOUS")))
                .build();
        log.trace("\n=========================================================================" +
                "\nAuthorized Request: {}" +
                "\n==========================================================================", authorizedRequest);
        var authorizedClient =  authorizedClientManager.authorize(authorizedRequest);

        log.trace("\n=========================================================================" +
                "\nAuthorized Client: {}" +
                "\n==========================================================================", authorizedClient);
        return authorizedClient;
    }
}
