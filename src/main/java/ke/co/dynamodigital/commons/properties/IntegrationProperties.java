package ke.co.dynamodigital.commons.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author arthurmita
 * created 05/03/2020 at 11:36
 **/
@Getter
@Setter
@Configuration
@ConfigurationProperties("integration")
public class IntegrationProperties {
    Map<String, String> endpoints;
}

