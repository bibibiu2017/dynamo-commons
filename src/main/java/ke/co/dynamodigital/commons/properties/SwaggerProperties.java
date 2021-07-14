package ke.co.dynamodigital.commons.properties;

import ke.co.dynamodigital.commons.properties.SwaggerProperties.Oauth2Security.Flow;
import lombok.Data;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 * @author arthurmita
 * created 25/10/2020 at 15:41
 **/
@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("swagger")
public class SwaggerProperties {
    private List<Server> servers = new ArrayList<>();
    private Info info = new Info();
    private Map<Flow, Oauth2Security> oauth2Security = new HashMap<>();

    @Data
    public static class Server {
        private String url;
        private String description;

        public io.swagger.v3.oas.models.servers.Server getOpenApiServer() {
            return new io.swagger.v3.oas.models.servers.Server()
                    .url(url).description(description);
        }
    }

    @Data
    public static class Info {
        private String title;
        private String version;
        private String description;
        private Contact contact = new Contact();

        @Data
        public static class Contact {
            private String url;
            private String email;
            private String name;

            public io.swagger.v3.oas.models.info.Contact getOpenApiContact() {
                return new io.swagger.v3.oas.models.info.Contact()
                        .url(url)
                        .email(email)
                        .name(name);
            }
        }

        public io.swagger.v3.oas.models.info.Info getOpenApiInfo() {
            return new io.swagger.v3.oas.models.info.Info()
                    .title(title)
                    .version(version)
                    .description(description)
                    .contact(contact.getOpenApiContact());
        }
    }

    @Data
    public static class Oauth2Security {
        private String name;
        private String description;
        private String tokenUrl;
        private String authorizationUrl;
        private String readScope;
        private String writeScope;

        public enum Flow {
            CLIENT, CODE, BASIC
        }
    }
}
