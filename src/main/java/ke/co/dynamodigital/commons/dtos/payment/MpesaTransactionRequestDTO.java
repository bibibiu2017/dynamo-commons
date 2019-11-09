package ke.co.dynamodigital.commons.dtos.payment;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author arthurmita
 * created 04/11/2019 at 22:35
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MpesaTransactionRequestDTO {

    @NotNull
    @NotBlank
    private String uuid;

    @NotNull
    @NotBlank
    private String amount;

    @NotNull
    @NotBlank
    private String transactionType;

    @NotNull
    @NotBlank
    private String phoneNumber;

    @NotNull
    @Singular
    private Map<String,String> requirements;
}
