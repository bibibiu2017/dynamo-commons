package ke.co.dynamodigital.commons.dtos.mpesa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bibibiu
 * created 2019-07-10 at 00:56
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MpesaConfirmationResponseDTO {

    @JsonProperty("C2BPaymentConfirmationResult")
    private String c2bPaymentConfirmationResult;
}
