package ke.co.dynamodigital.commons.models.accounts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Bibibiu
 * created 8/29/19 at 17:37
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CashWalletModel {
    private BigDecimal actualBalance;
    private BigDecimal availableBalance;
    private Map<String,Object> additionalInfo;
}
