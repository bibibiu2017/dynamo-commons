package ke.co.dynamodigital.commons.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import ke.co.dynamodigital.commons.properties.SwaggerProperties;
import ke.co.dynamodigital.commons.utils.SwaggerUtils;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author arthurmita
 * created 05/06/2021 at 03:15
 **/
@Configuration
@RequiredArgsConstructor
class SwaggerConfiguration {

    private final SwaggerProperties properties;

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI().info(loansInfo()).servers(loansServers()).components(components());
    }

    @Bean
    Info loansInfo() {
        return properties.getInfo().getOpenApiInfo();
    }

    @Bean
    List<Server> loansServers() {
        return properties.getServers().stream()
                .map(SwaggerProperties.Server::getOpenApiServer)
                .collect(Collectors.toUnmodifiableList());
    }

    @Bean
    Components components() {
        return new Components().securitySchemes(securitySchemes());
    }

    @Bean
    Map<String, SecurityScheme> securitySchemes() {
        Map<String, SecurityScheme> schemes = new HashMap<>();
        properties.getOauth2Security().forEach((flow, oauth2Security) -> {
            schemes.put(flow.name(), SwaggerUtils.extractSchemeFrom(flow, oauth2Security));
        });
        return schemes;
    }

    @Bean
    OpenApiCustomiser defaultResponses() {
        return new DefaultResponseCustomizer();
    }
}
