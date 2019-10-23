package ke.co.dynamodigital.commons.models.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bibibiu
 * created 9/14/19 at 16:07
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Permissions {

    @ApiModelProperty(
            position = 1,
            dataType = "enum",
            allowableValues = "READ,WRITE",
            notes = "If not provided defaults to write",
            example = "WRITE"
    )
    private String vendorPermission;
    @ApiModelProperty(
            position = 2,
            dataType = "enum",
            allowableValues = "READ,WRITE",
            example = "WRITE"
    )
    private String walletPermission;
}
