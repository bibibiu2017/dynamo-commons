package ke.co.dynamodigital.commons.config.annotations;


import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.http.HttpHeader;
import ke.co.dynamodigital.commons.utils.WireMockTestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.*;

import java.lang.annotation.*;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author arthurmita
 * created 06/01/2021 at 14:13
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ExtendWith(WireMockAuth.WireMockAuthExtension.class)
public @interface WireMockAuth {

    @Slf4j
    class WireMockAuthExtension implements BeforeAllCallback, AfterAllCallback {

        private static final String TOKEN = "token.json";
        private static final String AUTH_URL = "/auth/oauth/token";
        private static final WireMockServer authServer = WireMockTestUtils.configureServer(8000, true);

        @Override
        public void afterAll(ExtensionContext extensionContext) {
            log.debug("\n=====================================================" +
                    "\nStopped Auth Serve on port {}" +
                    "\n======================================================", authServer.port());
            authServer.stop();
        }

        @Override
        public void beforeAll(ExtensionContext extensionContext) {
            WireMockTestUtils.createStub(authServer, POST, AUTH_URL, OK, TOKEN, true, new HttpHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE));
            authServer.start();
            log.debug("\n=====================================================" +
                    "\nStarted Auth Serve on port {}" +
                    "\n======================================================", authServer.port());
        }
    }
}
