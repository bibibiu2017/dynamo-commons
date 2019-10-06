package ke.co.dynamodigital.commons.models.mpesa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Bibibiu
 * created 2019-07-30 at 17:06
 **/
@Data
public class AccountsStkCallback {
    @JsonProperty(required = true)
    private String merchantRequestId;
    @JsonProperty(required = true)
    private String checkoutRequestId;
    @JsonProperty(required = true)
    private Integer resultCode;
    private String resultDesc;
    private String mpesaReceiptNumber;
    private String amount;
    private String phoneNumber;
    private String balance;
    private String transactionDate;
    private String uuid;
}
