package ke.co.dynamodigital.commons.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

import static ke.co.dynamodigital.commons.utils.ObjectUtils.writeJson;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author arthurmita
 * created 27/10/2019 at 22:16
 **/
@Slf4j
@SpringBootTest
class Oauth2RestTemplateTest {

    @Autowired
    private OAuth2RestTemplate oauth2RestTemplate;

    @Test
    @DisplayName("AutowireOauth2RestTemplate-->templateAutowired")
    void test1() {
        //then
        assertThat(oauth2RestTemplate,is(notNullValue(OAuth2RestTemplate.class)));
        log.debug("Oauth2RestTemplateConfigurationResource\n{}",writeJson(oauth2RestTemplate.getResource()));
    }
}
