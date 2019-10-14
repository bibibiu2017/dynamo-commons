package ke.co.dynamodigital.commons.dtos.accounts;

import ke.co.dynamodigital.commons.models.accounts.LoanCountModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bibibiu
 * created 10/10/19 at 11:42
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanCountDTO {
    private LoanCountModel loanCountModel;
}
