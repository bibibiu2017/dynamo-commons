package ke.co.dynamodigital.commons.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author arthurmita
 * created 11/06/2020 at 20:06
 **/
@Getter
@Setter
@Primary
@Configuration
@ConfigurationProperties("security")
public class SecurityProperties {
    private String resourceId;
}
