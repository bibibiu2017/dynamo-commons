package ke.co.dynamodigital.commons.dtos.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author arthurmita
 * created 06/11/2019 at 12:08
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MpesaTransactionResponseDTO {
    @NotNull
    private Integer responseCode;
    @NotNull
    private String uuid;
    @Nullable
    private String transId;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private String phoneNumber;
    @NotNull
    private LocalDateTime createdTimestamp;
}
