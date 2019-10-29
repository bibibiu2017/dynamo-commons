package ke.co.dynamodigital.commons.services;

/**
 * @author arthurmita
 * created 27/10/2019 at 22:32
 **/
@FunctionalInterface
public interface AccessTokenProvider {
    String getAccessToken();
}
