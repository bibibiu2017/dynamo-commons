package ke.co.dynamodigital.commons.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bibibiu
 * created 9/11/19 at 21:02
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ClientRegistrationResponseDTO {
    private String clientName;
    private String clientKeyId;
    private String clientKeySecret;
    private String expires;
    private String message;
    private String timestamp;
    private String environment;
    private String application;
    private String organization;
    private String registeredCallback;
}
