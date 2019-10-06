package ke.co.dynamodigital.commons.dtos.mpesa;

import com.fasterxml.jackson.annotation.JsonProperty;
import ke.co.dynamodigital.commons.models.mpesa.MpesaStkBody;
import lombok.Data;

/**
 * @author Bibibiu
 * created 2019-07-11 at 05:49
 **/
@Data
public class MpesaStkCallbackDTO {

    @JsonProperty("Body")
    private MpesaStkBody mpesaStkBody;
}



