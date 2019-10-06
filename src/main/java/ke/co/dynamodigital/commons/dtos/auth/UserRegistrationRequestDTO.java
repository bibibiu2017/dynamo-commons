package ke.co.dynamodigital.commons.dtos.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Bibibiu
 * created 8/28/19 at 12:21
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequestDTO {

    @ApiModelProperty(
            hidden = true
    )
    private Long userId;

    @Size(min = 4, max = 16, message = "Invalid user name must have a minimum of 4 characters and a maximum of 16.")
    @ApiModelProperty(
            name = "idNumber",
            required = true,
            dataType = "string",
            example = "11223344",
            position = 1
    )
    private String username;

    @NotNull(message = "field cannot be null")
    @NotBlank(message = "field cannot be blank")
    @ApiModelProperty(
            name = "firstName",
            required = true,
            dataType = "string",
            example = "John",
            position = 3
    )
    private String firstName;

    @NotNull(message = "field cannot be null")
    @NotBlank(message = "field cannot be blank")
    @ApiModelProperty(
            name = "lastName",
            example = "Doe",
            required = true,
            dataType = "string",
            position = 4
    )
    private String lastName;

    @Size(min = 6, max = 20)
    @ApiModelProperty(
            name = "password",
            example = "password",
            required = true,
            dataType = "string",
            position = 2,
            notes = "must be at least 8 characters long but not more than 20 characters"

    )
    private String password;

    @Pattern(regexp = "(?:254|0)(7[0-9]{8})",message = "Invalid phone number. Must be a valid Kenyan phone number.")
    @ApiModelProperty(
            name = "phoneNumber",
            example = "254711223344",
            required = true,
            dataType = "string",
            position = 5,
            notes = "must be a valid Kenyan phone number we recommend a Safaricom line due to wallet top up"
    )
    private String phoneNumber;

    @Pattern(
            regexp = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$",
            message = "Invalid email address"
    )
    @ApiModelProperty(
            name = "email",
            example = "example@email.com",
            required = true,
            dataType = "string",
            position = 6,
            notes = "must be a valid Kenyan phone number we recommend a Safaricom line due to wallet top up"
    )
    private String email;
}
