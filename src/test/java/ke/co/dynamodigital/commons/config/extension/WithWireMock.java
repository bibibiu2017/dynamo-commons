package ke.co.dynamodigital.commons.config.extension;

import com.github.jenspiegsa.wiremockextension.*;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * @author arthurmita
 * created 06/01/2021 at 14:21
 **/
@ExtendWith({WireMockExtension.class})
public abstract class WithWireMock extends WithSoftAssertions {
    @InjectServer
    protected WireMockServer server;
}