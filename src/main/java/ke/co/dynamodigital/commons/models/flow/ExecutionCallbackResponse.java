package ke.co.dynamodigital.commons.models.flow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author arthurmita
 * created 27/06/2020 at 10:02
 **/
@Data
@RequiredArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExecutionCallbackResponse {
    @NotBlank
    private String message;
    private boolean error;
    private Integer valid = 0;
    private List<StepOption> options = new ArrayList<>();

    public ExecutionCallbackResponse(boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public ExecutionCallbackResponse(String message , List<StepOption> options) {
        this(false, message, options);
    }

    public ExecutionCallbackResponse(boolean error, String message , List<StepOption> options) {
        this.error = error;
        this.message = message;
        this.options = options;
    }

    public ExecutionCallbackResponse(Integer valid, String message) {
        this.error =false;
        this.valid = valid;
        this.message = message;
    }

    @Data
    @RequiredArgsConstructor
    @SuperBuilder(toBuilder = true)
    public static class StepOption {
        private String id;
        private String value;
        private String nextStep;
    }
}
