package ke.co.dynamodigital.commons.models.flow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Data
@Jacksonized
@SuperBuilder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlowCallbackRequest {
    @NotBlank
    private String address;

    @NotBlank
    private String merchantId;

    @NotBlank
    private String flow;

    @NotBlank
    private String step;

    @NotBlank
    private String incomingMessage;

    @NotNull
    private RequestType requestType;

    @Builder.Default
    private Map<String, String> responses = new HashMap<>();

    public enum RequestType {
        OPTIONS, VALIDATION, CALLBACK
    }
}
