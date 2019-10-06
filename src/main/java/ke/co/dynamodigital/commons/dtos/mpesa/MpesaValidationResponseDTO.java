package ke.co.dynamodigital.commons.dtos.mpesa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bibibiu
 * created 2019-07-09 at 22:17
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MpesaValidationResponseDTO {

    @JsonProperty("ResultCode")
    private Integer resultCode;

    @JsonProperty("ResultDesc")
    private String resultDesc;
}
