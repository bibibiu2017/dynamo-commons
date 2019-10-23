package ke.co.dynamodigital.commons.dtos.mpesa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bibibiu
 * created 2019-07-09 at 12:52
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUrlResponseDTO {

    @JsonProperty("ConversationID")
    private String conversionId;

    @JsonProperty("OriginatorConverstionID")
    private String originatorConversionId;

    @JsonProperty("ResponseDescription")
    private String responseDescription;
}
