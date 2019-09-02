package ke.co.dynamodigital.commons.models.accounts;

import io.swagger.annotations.ApiModelProperty;
import ke.co.dynamodigital.commons.models.accounts.CashWalletModel;
import ke.co.dynamodigital.commons.models.accounts.LoanWallet;
import ke.co.dynamodigital.commons.models.accounts.Meter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

/**
 * @author Bibibiu
 * created 8/29/19 at 17:37
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountsUserInfo {
    @ApiModelProperty(position = 1)
    private CashWalletModel cashWallet;
    @ApiModelProperty(position = 2)
    private LoanWallet loanWallet;
    @ApiModelProperty(position = 3)
    private Set<Meter> meters;
    @ApiModelProperty(position = 4)
    private Map<String,Object> additionalInfo;
}
