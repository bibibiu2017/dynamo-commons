package ke.co.dynamodigital.commons.models.flow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author arthurmita
 * created 27/06/2020 at 10:02
 **/
@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlowCallbackResponse {
    @NotBlank
    private String message;
    private boolean error;
    private Integer valid = 0;
    private List<String> options = new ArrayList<>();

    public FlowCallbackResponse(boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public FlowCallbackResponse(String message , List<String> options) {
        this(false, message, options);
    }

    public FlowCallbackResponse(boolean error, String message , List<String> options) {
        this.error = error;
        this.message = message;
        this.options = options;
    }

    public FlowCallbackResponse(Integer valid, String message) {
        this.error =false;
        this.valid = valid;
        this.message = message;
    }
}
