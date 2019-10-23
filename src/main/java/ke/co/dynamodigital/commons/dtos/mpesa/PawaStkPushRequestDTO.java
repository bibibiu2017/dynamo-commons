package ke.co.dynamodigital.commons.dtos.mpesa;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Bibibiu
 * created 2019-07-11 at 01:53
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PawaStkPushRequestDTO {

    @ApiModelProperty(
            notes = "Amount of money to request using stk push minimum 5 maximum 20000",
            example = "1000",
            dataType = "string",
            position = 3,
            required = true
    )
    private String amount;

    @ApiModelProperty(
            notes = "Mpesa valid phone number to make stk push must start with 254",
            example = "254711223344",
            dataType = "string",
            position = 1,
            required = true
    )
    private String phoneNumber;

    @ApiModelProperty(
            notes = "meter number to purchase token for",
            example = "14140384281",
            dataType = "string",
            position = 2,
            required = true
    )
    private String meterNumber;

    @ApiModelProperty(
            notes = "Description of the payment",
            example = "Token Purchase",
            dataType = "string",
            position = 5,
            required = false
    )
    private String description;

    @ApiModelProperty(
            notes = "Mpesa online transaction type",
            example = "CustomerPayBillOnline",
            dataType = "string",
            position = 4,
            allowableValues = "CustomerPayBillOnline, CustomerBuyGoodsOnline",
            required = true
    )
    private String transactionType;

    private String uuid;

    @ApiModelProperty(
            hidden = true
    )
    private Date timestamp;


}