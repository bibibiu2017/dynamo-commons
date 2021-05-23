package ke.co.dynamodigital.commons.feign;

import feign.*;
import feign.codec.ErrorDecoder;
import ke.co.dynamodigital.commons.error.GenericHttpException;
import ke.co.dynamodigital.commons.utils.LogUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.List;

import static ke.co.dynamodigital.commons.error.DefaultServiceErrorMessage.extractEmbeddedErrorMessage;

/**
 * @author arthurmita
 * created 23/05/2021 at 14:31
 **/
@Slf4j
@RequiredArgsConstructor
public class DefaultErrorDecoder implements ErrorDecoder {

    private final List<HttpStatus> retryableStatusCodes;
    private final List<HttpStatus> propagatableStatusCodes;

    @Override
    public Exception decode(String methodKey, Response response) {
        LogUtils.logError(log,"Feign Error Occurred, Method[" + methodKey + "]", response.reason(), new Exception(response.reason()));
        HttpStatus status = HttpStatus.valueOf(response.status());
        if (retryableStatusCodes.contains(status))
            throw new RetryableException(response.status(), response.toString(), response.request().httpMethod(), null, response.request());
        if (propagatableStatusCodes.contains(status))
            throw new GenericHttpException(extractEmbeddedErrorMessage(response), status);
        return new ErrorDecoder.Default().decode(methodKey, response);
    }
}
