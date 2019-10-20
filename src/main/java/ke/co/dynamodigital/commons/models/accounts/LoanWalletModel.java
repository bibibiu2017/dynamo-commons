package ke.co.dynamodigital.commons.models.accounts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Bibibiu
 * created 8/29/19 at 17:38
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanWalletModel {
    private BigDecimal loanBalance;
    private BigDecimal loanLimit;
    private Map<String, Object> additionalInfo;
}
