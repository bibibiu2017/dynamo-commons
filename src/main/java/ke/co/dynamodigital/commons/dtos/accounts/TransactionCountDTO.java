package ke.co.dynamodigital.commons.dtos.accounts;

import ke.co.dynamodigital.commons.models.accounts.TransactionCountModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bibibiu
 * created 10/10/19 at 11:43
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCountDTO {
    private TransactionCountModel transactionCountModel;
}
