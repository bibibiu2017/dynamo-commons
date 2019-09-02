package ke.co.dynamodigital.commons.utils;

import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.Map;
import java.util.UUID;

/**
 * @author Bibibiu
 * created 8/28/19 at 10:41
 **/
public class SecurityUtils {

    public static Map<String, Object> getExtraInfo(Authentication auth) {
        OAuth2AuthenticationDetails oauthDetails = (OAuth2AuthenticationDetails) auth.getDetails();
        return (Map<String, Object>) oauthDetails.getDecodedDetails();
    }

    public static Map<String, Object> getExtraInfo() {
        OAuth2AuthenticationDetails oauthDetails =
                (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return (Map<String, Object>) oauthDetails.getDecodedDetails();

    }

    @SneakyThrows
    public static String uuidGenerator() {
        return UUID.randomUUID().toString();
    }
}
