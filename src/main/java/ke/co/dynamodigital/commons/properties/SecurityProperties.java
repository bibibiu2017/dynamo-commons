package ke.co.dynamodigital.commons.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author arthurmita
 * created 11/06/2020 at 20:06
 **/
@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("security")
public class SecurityProperties {
    private String resourceId;
    private BasicAuth basicAuth = new BasicAuth();

    @Data
    public static class BasicAuth {
        private String username;
        private String password;
    }
}
