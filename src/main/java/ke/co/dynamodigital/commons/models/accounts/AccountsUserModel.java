package ke.co.dynamodigital.commons.models.accounts;

import io.swagger.annotations.ApiModelProperty;
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
public class AccountsUserModel {
    @ApiModelProperty(position = 1)
    private CashWalletModel cashWalletModel;
    @ApiModelProperty(position = 2)
    private LoanWalletModel loanWalletModel;
    @ApiModelProperty(position = 3)
    private Set<MeterModel> meterModels;
    @ApiModelProperty(position = 4)
    private Map<String,Object> additionalInfo;
}
