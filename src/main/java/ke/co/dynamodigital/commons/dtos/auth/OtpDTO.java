package ke.co.dynamodigital.commons.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bibibiu
 * created 8/28/19 at 13:46
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OtpDTO {
    private String username;
    private Integer otp;
}
