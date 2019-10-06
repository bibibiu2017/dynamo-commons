package ke.co.dynamodigital.commons.dtos.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bibibiu
 * created 8/28/19 at 12:36
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationResponseDTO {
    @ApiModelProperty(position = 1)
    private String username;
    @ApiModelProperty(position = 2)
    private String message;
    @ApiModelProperty(position = 3)
    private String expiresIn;
    @ApiModelProperty(position = 4)
    private Integer testOtp;
}
