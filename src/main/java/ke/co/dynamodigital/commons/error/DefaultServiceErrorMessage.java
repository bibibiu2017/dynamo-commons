package ke.co.dynamodigital.commons.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.io.CharStreams;
import feign.Response;
import ke.co.dynamodigital.commons.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.nio.charset.StandardCharsets;

/**
 * @author lawrence
 * created 29/01/2020 at 03:28
 **/

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DefaultServiceErrorMessage {
    private String timestamp;
    private int status;
    private String message;
    private String path;

    public static String extractEmbeddedErrorMessage(Response response) {
        try {
            String responseBody = CharStreams.toString(response.body().asReader(StandardCharsets.UTF_8));
            DefaultServiceErrorMessage defaultServiceErrorMessage = ObjectUtils.readJson(responseBody, DefaultServiceErrorMessage.class);
            return defaultServiceErrorMessage.message;
        } catch (Exception e) {
            return response.reason();
        }
    }
}
