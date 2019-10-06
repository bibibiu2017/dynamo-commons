package ke.co.dynamodigital.commons.dtos.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

/**
 * @author Bibibiu
 * created 9/12/19 at 20:11
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OrganisationRegistrationRequestDTO {

    @ApiModelProperty(hidden = true)
    private Long userId;

    @ApiModelProperty(
            notes = "name of organization being created. Must be unique" +
                    "in dynamo namespace",
            dataType = "string",
            example = "DynamoDigital",
            position = 1
    )
    private String organizationName;

    @ApiModelProperty(
            notes = "Must be a valid email address",
            dataType = "string",
            example = "email@example.com",
            position = 3

    )
    @Email(message = "must be a valid email address")
    private String email;


    @ApiModelProperty(
            notes = "Valid Kenyan phone number",
            dataType = "string",
            example = "2547XXXXXXXX",
            position = 2
    )
    @Pattern(regexp = "(?:254|0)(7[0-9]{8})",message = "Invalid phone number. Must be a valid Kenyan phone number.")
    private String phoneNumber;
}
