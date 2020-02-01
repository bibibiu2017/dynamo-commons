package ke.co.dynamodigital.commons.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author arthurmita
 * created 31/01/2020 at 13:40
 **/
@SuppressWarnings("unchecked")
public class CustomAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {

        var authorities = ((Collection<String>) jwt.getClaims().get("authorities"));
        var scopes = ((Collection<String>) jwt.getClaims().get("scope"));

        Stream<GrantedAuthority> roleAuthorities = Stream.empty();
        Stream<GrantedAuthority> roleScopes = Stream.empty();


        if (authorities != null && !authorities.isEmpty()) {
            roleAuthorities = authorities.stream()
                    .map(role -> {
                        if (!role.startsWith("ROLE")) {
                            role = "ROLE_" + role;
                        }
                        return role;
                    })
                    .map(SimpleGrantedAuthority::new);
        }

        if (scopes != null && !scopes.isEmpty()) {
            roleScopes = scopes.stream()
                    .map(scope -> "SCOPE_" + scope)
                    .map(SimpleGrantedAuthority::new);
        }


        Collection<GrantedAuthority> allAuthorities = Stream
                .concat(roleAuthorities, roleScopes)
                .collect(Collectors.toList());

        AbstractAuthenticationToken authenticationToken = new AbstractAuthenticationToken(allAuthorities) {
            @Override
            public Object getCredentials() {
                return jwt;
            }

            @Override
            public Object getPrincipal() {
                return jwt;
            }
        };

        authenticationToken.setAuthenticated(true);
        return authenticationToken;
    }

}
