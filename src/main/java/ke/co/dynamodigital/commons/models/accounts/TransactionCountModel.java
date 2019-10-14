package ke.co.dynamodigital.commons.models.accounts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bibibiu
 * created 10/10/19 at 11:39
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCountModel {
    private Long allTransactions;
    private Long failedTransactions;
    private Long completeTransactions;
    private Long pendingTransactions;
}
