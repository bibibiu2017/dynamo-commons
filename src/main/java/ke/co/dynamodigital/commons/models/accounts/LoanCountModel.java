package ke.co.dynamodigital.commons.models.accounts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bibibiu
 * created 10/10/19 at 11:38
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanCountModel {
    private Long loanCount;
    private double par;
}
