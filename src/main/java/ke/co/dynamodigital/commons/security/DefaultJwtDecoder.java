package ke.co.dynamodigital.commons.security;

import lombok.*;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.jwt.*;

/**
 * @author arthurmita
 * created 18/06/2021 at 15:30
 **/
@RequiredArgsConstructor
public class DefaultJwtDecoder implements JwtDecoder {

    private final String jwkSetUri;
    private final String resourceId;

    @Override
    public Jwt decode(String token) throws JwtException {
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
        OAuth2TokenValidator<Jwt> aDefault = JwtValidators.createDefault();
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(aDefault, audTokenValidator());
        jwtDecoder.setJwtValidator(withAudience);
        return jwtDecoder.decode(token);
    }

    public OAuth2TokenValidator<Jwt> audTokenValidator() {
        return jwt -> {
            val error = new OAuth2Error("INVALID_TOKEN", "Token does not contain aud-->" + resourceId, jwt.getHeaders().get("iss").toString());
            val aud = jwt.getAudience();
            return aud.contains(resourceId) ? OAuth2TokenValidatorResult.success() : OAuth2TokenValidatorResult.failure(error);
        };
    }
}
