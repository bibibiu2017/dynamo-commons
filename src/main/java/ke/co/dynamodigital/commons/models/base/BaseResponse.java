package ke.co.dynamodigital.commons.models.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author Bibibiu
 * created 8/28/19 at 13:43
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Deprecated(forRemoval = true,since = "1.0.0")
@SuperBuilder(toBuilder = true)
public class BaseResponse {
    @JsonProperty("timestamp")
    private LocalDateTime timestamp;
    private String message;
    @Singular("additionalInfo")
    private Map<String, Object> additionalInfo;
}
