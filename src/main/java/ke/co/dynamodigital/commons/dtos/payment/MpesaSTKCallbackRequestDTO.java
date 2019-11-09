package ke.co.dynamodigital.commons.dtos.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import ke.co.dynamodigital.commons.models.mpesa.MpesaStkBody;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author arthurmita
 * created 07/11/2019 at 05:19
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MpesaSTKCallbackRequestDTO {
    @JsonProperty("Body")
    private MpesaStkBody mpesaStkBody;
}
