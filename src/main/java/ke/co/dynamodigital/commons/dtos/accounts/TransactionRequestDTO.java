package ke.co.dynamodigital.commons.dtos.accounts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author Bibibiu
 * created 8/29/19 at 04:25
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TransactionRequestDTO {

    @NotNull
    @NotBlank
    private String transactionType;
    @NotNull
    @NotBlank
    private String transactionSource;
    @NotNull
    @NotBlank
    private String paymentMode;
    @NotNull
    @NotBlank
    private String uuid;
    private Map<String, Object> requirements;
}
