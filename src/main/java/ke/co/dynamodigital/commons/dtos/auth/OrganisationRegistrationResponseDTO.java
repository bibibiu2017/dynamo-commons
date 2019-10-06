package ke.co.dynamodigital.commons.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bibibiu
 * created 9/12/19 at 21:34
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OrganisationRegistrationResponseDTO {
    private String organizationName;
    private String email;
    private String phoneNumber;
    @Builder.Default
    private boolean verified = false;
}
