package ke.co.dynamodigital.commons.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import ke.co.dynamodigital.commons.properties.SwaggerProperties;
import ke.co.dynamodigital.commons.utils.SwaggerUtils;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author arthurmita
 * created 05/06/2021 at 03:15
 **/
@RequiredArgsConstructor
public class DefaultOpenApi {

    private final SwaggerProperties properties;

    public OpenAPI openApi() {
        return new OpenAPI().info(loansInfo()).servers(loansServers()).components(components());
    }

    private Info loansInfo() {
        return properties.getInfo().getOpenApiInfo();
    }

    List<Server> loansServers() {
        return properties.getServers().stream()
                .map(SwaggerProperties.Server::getOpenApiServer)
                .collect(Collectors.toUnmodifiableList());
    }

    private Components components() {
        return new Components().securitySchemes(securitySchemes());
    }

    private Map<String, SecurityScheme> securitySchemes() {
        Map<String, SecurityScheme> schemes = new HashMap<>();
        properties.getOauth2Security().forEach((flow, oauth2Security) -> {
            schemes.put(flow.name(), SwaggerUtils.extractSchemeFrom(flow, oauth2Security));
        });
        return schemes;
    }
}
