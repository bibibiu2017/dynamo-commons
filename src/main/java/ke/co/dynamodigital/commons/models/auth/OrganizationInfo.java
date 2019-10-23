package ke.co.dynamodigital.commons.models.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bibibiu
 * created 9/13/19 at 02:11
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OrganizationInfo {
    private Long organizationId;
    private String organizationName;
    private String email;
    private String phoneNumber;
    @Builder.Default
    private boolean verified = false;
}
