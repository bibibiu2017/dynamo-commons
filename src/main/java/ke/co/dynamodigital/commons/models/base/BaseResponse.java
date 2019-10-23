package ke.co.dynamodigital.commons.models.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * @author Bibibiu
 * created 8/28/19 at 13:43
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    @Deprecated
    @JsonProperty("time")
    private Date timestamp;
    @JsonProperty("timestamp")
    private LocalDateTime time;
    private String message;
    private Map<String, Object> additionalInfo;
}
