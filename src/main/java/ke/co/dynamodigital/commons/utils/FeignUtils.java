package ke.co.dynamodigital.commons.utils;

import feign.RequestInterceptor;
import ke.co.dynamodigital.commons.security.AuthorizedClientManager;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.util.Assert;

/**
 * @author arthurmita
 * created 26/04/2020 at 00:58
 **/
@Slf4j
@UtilityClass
public class FeignUtils {

    public RequestInterceptor intercept(AuthorizedClientManager authorizedClient, String clientAuth, String bearerTokenType) {
        return requestTemplate -> {
            Assert.notNull(authorizedClient, "Authorized client manager cannot be null");
            log.trace("\n=======================================================================" +
                    "\nGetting Authorization for client with name: {}" +
                    "\n======================================================================", clientAuth);
            OAuth2AuthorizedClient authorized = authorizedClient.authorize(clientAuth);

            Assert.notNull(authorized, "Authorized client cannot be null");
            //Get token
            final OAuth2AccessToken accessToken = authorized.getAccessToken();

            log.trace("Access Token: {}", accessToken);

            String token = accessToken.getTokenValue();
            if (requestTemplate.headers().containsKey(HttpHeaders.AUTHORIZATION))
                log.debug("Authorization header has already been set");
            else if (token == null)
                log.error("Can not get existing token for request, ignore if resource is not secured");
            else
                requestTemplate.header(HttpHeaders.AUTHORIZATION, String.format("%s %s", bearerTokenType, token));
        };
    }

    public RequestInterceptor intercept(AuthorizedClientManager authorizedClient, String clientAuth) {
        return intercept(authorizedClient, clientAuth, "Bearer");
    }
}
