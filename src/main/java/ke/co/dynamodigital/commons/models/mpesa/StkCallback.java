package ke.co.dynamodigital.commons.models.mpesa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Bibibiu
 * created 2019-07-11 at 06:57
 **/
@Data
public class StkCallback {

    @JsonProperty("MerchantRequestID")
    private String merchantRequestId;

    @JsonProperty("CheckoutRequestID")
    private String checkoutRequestId;

    @JsonProperty("ResultCode")
    private Integer resultCode;

    @JsonProperty("ResultDesc")
    private String resultDesc;

    @JsonProperty("CallbackMetadata")
    private CallbackMetadata callbackMetadata;
}
