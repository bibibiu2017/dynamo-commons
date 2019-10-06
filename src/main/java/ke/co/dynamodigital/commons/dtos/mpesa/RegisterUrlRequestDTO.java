package ke.co.dynamodigital.commons.dtos.mpesa;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bibibiu
 * created 2019-07-09 at 12:45
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUrlRequestDTO {

    @JsonProperty("ShortCode")
    @ApiModelProperty(
            notes = "mpesa business short code default 601395",
            example = "601395",
            dataType = "string",
            position = 1,
            required = true
    )
    private String shortCode;

    @JsonProperty("ResponseType")
    @ApiModelProperty(
            notes = "mpesa response type that determines what happens when validation" +
                    "callback cannot be reached default Cancelled",
            example = "Cancelled",
            dataType = "string",
            position = 4,
            allowableValues = "Cancelled, Completed"
    )
    private String responseType;

    @JsonProperty("ConfirmationURL")
    @ApiModelProperty(
            notes = "Callback url for mpesa confirmation",
            example = "http://example/confirmation.com",
            dataType = "string",
            position = 2,
            required = true
    )
    private String confirmationUrl;

    @JsonProperty("ValidationURL")
    @ApiModelProperty(
            notes = "Callback url for mpesa validation if enabled",
            example = "http://example/validation.com",
            dataType = "string",
            position = 3,
            required = true
    )
    private String validationUrl;
}
