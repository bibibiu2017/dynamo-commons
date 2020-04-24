package ke.co.dynamodigital.commons.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
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
@UtilityClass
public class SecurityUtils {

    @SneakyThrows
    public String uuidGenerator() {
        return UUID.randomUUID().toString();
    }

    /**
     * Gets extra token info. This is the info stored in the
     * token claims.
     * @param key info name
     * @param <T> the class type of extracted info
     * @param type type of info to get
     * @return found info
     */
    @SneakyThrows
    @Deprecated(forRemoval = true, since = "version 0.2.0")
    public static <T> T getExtraInfo(String key, Class<T> type) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Assert.notNull(auth,"Authentication cannot be null");
        Jwt claims = (Jwt) auth.getCredentials();
        return (T) claims.getClaims().get(key);
    }

    /**
     * Gets extra token info. This is the info stored in the
     * token claims.
     * @param key info name
     * @param <T> the class type of extracted info
     * @return found info
     */
    public static <T> T getExtraInfo(String key) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Assert.notNull(auth,"Authentication cannot be null");
        Jwt claims = (Jwt) auth.getCredentials();
        return (T) claims.getClaims().get(key);
    }
}
