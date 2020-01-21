package ke.co.dynamodigital.commons.utils;

import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.Assert;

import java.util.UUID;

/**
 * @author Bibibiu
 * created 8/28/19 at 10:41
 **/
@SuppressWarnings("unchecked")
public class SecurityUtils {

    @SneakyThrows
    public static String uuidGenerator() {
        return UUID.randomUUID().toString();
    }

    /**
     * Gets extra token info. This is the info stored in the
     * token claims.
     * @param key info name
     * @param type type of info to get
     * @return found info
     */
    @SneakyThrows
    public static <T> T getExtraInfo(String key, Class<T> type) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Assert.notNull(auth,"Authentication cannot be null");
        Jwt claims = (Jwt) auth.getCredentials();
        return (T) claims.getClaims().get(key);
    }
}
