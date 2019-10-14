package ke.co.dynamodigital.commons.models.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author Bibibiu
 * created 8/29/19 at 17:40
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserInfo {

    @ApiModelProperty(hidden = true)
    private Long userId;
    @ApiModelProperty(
            position = 1
    )
    private String email;
    @ApiModelProperty(
            position = 2
    )
    private String phoneNumber;
    @ApiModelProperty(
            position = 3
    )
    private String idNumber;
    @ApiModelProperty(position = 4)
    private Map<String, Object> additionalInfo;
}
