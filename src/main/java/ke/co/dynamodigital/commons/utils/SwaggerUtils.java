package ke.co.dynamodigital.commons.utils;

import io.swagger.v3.oas.models.security.*;
import ke.co.dynamodigital.commons.properties.SwaggerProperties.Oauth2Security;
import ke.co.dynamodigital.commons.properties.SwaggerProperties.Oauth2Security.Flow;
import lombok.experimental.UtilityClass;

import static io.swagger.v3.oas.models.security.SecurityScheme.In.HEADER;
import static io.swagger.v3.oas.models.security.SecurityScheme.Type.OAUTH2;

/**
 * @author arthurmita
 * created 05/06/2021 at 03:08
 **/
@UtilityClass
public class SwaggerUtils {

    public SecurityScheme extractSchemeFrom(Flow flow, Oauth2Security security) {
        SecurityScheme scheme = new SecurityScheme().type(OAUTH2).in(HEADER).bearerFormat("JWT")
                .name(security.getName()).description(security.getDescription());
        Scopes scopes = new Scopes()
                .addString(security.getReadScope(), "Read scope")
                .addString(security.getWriteScope(), "Write scope");
        switch (flow) {
            case CODE:
                scheme.flows(new OAuthFlows().authorizationCode(new OAuthFlow()
                        .tokenUrl(security.getTokenUrl())
                        .authorizationUrl(security.getAuthorizationUrl())
                        .scopes(scopes)));
                break;
            case CLIENT:
                scheme.flows(new OAuthFlows().clientCredentials(new OAuthFlow()
                        .tokenUrl(security.getTokenUrl())
                        .scopes(scopes)));
                break;
        }
        return scheme;
    }
}
