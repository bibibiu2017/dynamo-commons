package ke.co.dynamodigital.commons.dtos.auth;

import io.swagger.annotations.ApiModelProperty;
import ke.co.dynamodigital.commons.models.auth.Permissions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Bibibiu
 * created 9/11/19 at 20:09
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ClientRegistrationRequestDTO {

    @ApiModelProperty(hidden = true)
    private Long userId;

    @ApiModelProperty(
            notes = "Name of client to create. Must be unique in organization name space",
            required = true,
            dataType = "string"

    )
    @NotBlank(message = "Client name cannot be blank")
    @NotNull(message = "Client name cannot be null")
    private String clientName;

    @ApiModelProperty(
            notes = "name of organisation the API is being registered",
            required = true,
            dataType = "string",
            position = 1
    )
    @NotBlank(message = "Organization name cannot be blank")
    @NotNull(message = "Organization name cannot be null")
    private String organisationName;

    @ApiModelProperty(
            notes = "Application Environment",
            allowableValues = "sandbox,production",
            dataType = "enum",
            example = "sandbox",
            position = 4
    )
    @NotBlank(message = "Environment cannot be blank")
    @NotNull(message = "Environment cannot be null")
    private String environment;

    @ApiModelProperty(
            name = "AppSubscription",
            required = true,
            position = 2,
            dataType = "enum",
            notes = "API to subscribe to",
            allowableValues = "vendor",
            example = "vendor"
    )
    @NotNull(message = "Subscription cannot be null")
    @NotBlank(message = "Subscription cannot be blank")
    private String subscription;

    @ApiModelProperty(
            notes = "Callback url for for asynchronous processing",
            dataType = "string",
            position = 3,
            example = "http://example.com"
    )
    private String callbackUrl;

    @ApiModelProperty(
            notes = "Permissions to give API client",
            dataType = "object",
            position = 5
    )
    private Permissions permissions;
}
