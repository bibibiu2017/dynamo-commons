package ke.co.dynamodigital.commons.models.accounts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Bibibiu
 * created 9/10/19 at 19:53
 **/
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionModel {
    private String referenceNumber;
    private String transactionType;
    private String transactionState;
    private String transactionSource;
    private String paymentMode;
    private String transactionDate;
}
