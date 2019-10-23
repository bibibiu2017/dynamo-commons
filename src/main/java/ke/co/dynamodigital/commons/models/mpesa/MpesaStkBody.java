package ke.co.dynamodigital.commons.models.mpesa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Bibibiu
 * created 2019-07-11 at 06:55
 **/
@Data
public class MpesaStkBody {
    @JsonProperty("stkCallback")
    private StkCallback stkCallback;

}
