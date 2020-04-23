package ke.co.dynamodigital.commons.utils;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.http.Fault;
import com.github.tomakehurst.wiremock.http.HttpHeader;
import com.github.tomakehurst.wiremock.http.HttpHeaders;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * @author lawrence
 * created 29/01/2020 at 01:00
 **/
@Slf4j
@UtilityClass
public class WireMockTestUtils {


    /**
     * Simple wireMock server configuration
     *
     * @param port        port to listen on
     * @param verboseLogs turn on/off verbose logging
     * @return configured WireMock server instance
     */
    public WireMockServer configureServer(int port, boolean verboseLogs) {
        return new WireMockServer(WireMockConfiguration.options().port(port).notifier(new ConsoleNotifier(verboseLogs)));
    }

    /**
     * Generate single wireMock stub
     *
     * @param wireMockServer       server instance
     * @param httpHeaders          http headers
     * @param requestMethod        request method to use
     * @param url                  url to match
     * @param httpStatus           http status to return
     * @param responseBody         body to return
     * @param responseBodyFromFile read body from file or not
     */
    public void createStub(WireMockServer wireMockServer, RequestMethod requestMethod, String url, HttpStatus httpStatus, String responseBody, boolean responseBodyFromFile, HttpHeader... httpHeaders) {

        if (responseBodyFromFile)
            wireMockServer.stubFor(
                    constructMappingBuilder(requestMethod, url)
                            .willReturn(aResponse()
                                    .withHeaders(new HttpHeaders(httpHeaders))
                                    .withStatus(httpStatus.value())
                                    .withBodyFile(responseBody)));
        else
            wireMockServer.stubFor(
                    constructMappingBuilder(requestMethod, url)
                            .willReturn(aResponse()
                                    .withHeaders(new HttpHeaders(httpHeaders))
                                    .withStatus(httpStatus.value())
                                    .withBody(responseBody)));
    }

    /**
     * Create fault stub
     *
     * @param wireMockServer server instance
     * @param requestMethod  request method to use
     * @param url            url to match
     * @param fault          fault to execute
     */
    public void createFaultStub(WireMockServer wireMockServer, RequestMethod requestMethod, String url, Fault fault) {
        wireMockServer.stubFor(
                constructMappingBuilder(requestMethod, url)
                        .willReturn(aResponse()
                                .withFault(fault)));
    }

    /**
     * Generate scenario wireMock stub
     *
     * @param wireMockServer       server instance
     * @param httpHeaders          http headers
     * @param requestMethod        request method to use
     * @param url                  url to match
     * @param scenario             scenario to join
     * @param currentScenarioState current state
     * @param nextScenarioState    next state
     * @param httpStatus           http status to return
     * @param responseBody         body to return
     * @param responseBodyFromFile read body from file or not
     */
    public void createScenarioStub(WireMockServer wireMockServer, RequestMethod requestMethod, String url, String scenario, String currentScenarioState, String nextScenarioState,
                                   HttpStatus httpStatus, String responseBody, boolean responseBodyFromFile, HttpHeader... httpHeaders) {
        log.debug("scenario: {}", scenario);
        log.debug("currentState: {}", currentScenarioState);
        log.debug("nextState: {}", nextScenarioState);

        if (responseBodyFromFile)
            wireMockServer.stubFor(
                    constructMappingBuilder(requestMethod, url)
                            .inScenario(scenario)
                            .whenScenarioStateIs(currentScenarioState)
                            .willReturn(aResponse()
                                    .withHeaders(new HttpHeaders(httpHeaders))
                                    .withStatus(httpStatus.value())
                                    .withBodyFile(responseBody))
                            .willSetStateTo(nextScenarioState));
        else
            wireMockServer.stubFor(
                    constructMappingBuilder(requestMethod, url)
                            .inScenario(scenario)
                            .whenScenarioStateIs(currentScenarioState)
                            .willReturn(aResponse()
                                    .withHeaders(new HttpHeaders(httpHeaders))
                                    .withStatus(httpStatus.value())
                                    .withBody(responseBody))
                            .willSetStateTo(nextScenarioState));
    }


    /**
     * Construct wire mock mapping builder
     *
     * @param requestMethod request method to use
     * @param url           url to use
     * @return constructed mapping builder
     */
    private MappingBuilder constructMappingBuilder(RequestMethod requestMethod, String url) {

        MappingBuilder mappingBuilder;
        switch (requestMethod) {
            case GET:
                mappingBuilder = get(urlPathMatching(url));
                break;
            case POST:
                mappingBuilder = post(urlPathMatching(url));
                break;
            case PUT:
                mappingBuilder = put(urlPathMatching(url));
                break;
            case DELETE:
                mappingBuilder = delete(urlPathMatching(url));
                break;
            default:
                throw new UnsupportedOperationException("Unsupported Request Method: " + requestMethod);
        }
        return mappingBuilder;
    }
}
