package ke.co.dynamodigital.commons.config.annotations;

import one.util.streamex.StreamEx;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.*;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.test.context.support.*;

import java.lang.annotation.*;
import java.util.*;

import static org.springframework.security.oauth2.jwt.JwtClaimNames.SUB;

/**
 * @author arthurmita
 * created 03/11/2020 at 15:49
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@WithSecurityContext(factory = WithMockJwt.WithMockJwtSecurityContextFactory.class)
public @interface WithMockJwt {
    String username() default "email@example.com";

    String userId() default "user";

    String clientId() default "client";

    String[] audiences() default {};

    String[] scopes() default {};

    String[] roles() default {};

    JwtClaim[] claims() default {};

    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface JwtClaim {

        String key();

        String claim();
    }

    /**
     * @author arthurmita
     * created 03/11/2020 at 15:52
     **/
    final class WithMockJwtSecurityContextFactory implements WithSecurityContextFactory<WithMockJwt> {

        @Override
        public SecurityContext createSecurityContext(WithMockJwt annotation) {
            SecurityContext context = SecurityContextHolder.createEmptyContext();

            Jwt.Builder builder = Jwt.withTokenValue("token").header("alg", "none").claim(SUB, "user");
            StreamEx<GrantedAuthority> roles = StreamEx.empty();
            StreamEx<GrantedAuthority> scopes = StreamEx.empty();

            if (annotation.claims().length > 0)
                Set.of(annotation.claims()).forEach(jwtClaim -> builder.claim(jwtClaim.key(), jwtClaim.claim()));
            if (!annotation.userId().isBlank())
                builder.claim("user_id", annotation.userId());
            if (!annotation.clientId().isBlank())
                builder.claim("client_id", annotation.clientId());
            if (annotation.roles().length > 0)
                roles = StreamEx.of(Set.of(annotation.roles())).map(this::mapRole).map(SimpleGrantedAuthority::new);
            if (annotation.scopes().length > 0)
                scopes = StreamEx.of(annotation.scopes()).map(this::mapScope).map(SimpleGrantedAuthority::new);

            builder.audience(Set.of(annotation.audiences()));
            builder.claim("username", annotation.username());

            Jwt jwt = builder.build();
            Collection<GrantedAuthority> grantedAuthorities = roles.append(scopes).toImmutableList();
            Authentication authentication = new UsernamePasswordAuthenticationToken(jwt, jwt, grantedAuthorities);
            context.setAuthentication(authentication);

            return context;
        }

        private String mapRole(String role) {
            return role.startsWith("ROLE_") ? role : "ROLE_" + role;
        }

        private String mapScope(String scope) {
            return scope.startsWith("SCOPE_") ? scope : "SCOPE_" + scope;
        }
    }
}
