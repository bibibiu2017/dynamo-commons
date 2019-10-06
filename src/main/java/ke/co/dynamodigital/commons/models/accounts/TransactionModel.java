package ke.co.dynamodigital.commons.models.accounts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bibibiu
 * created 9/10/19 at 19:53
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionModel {
    private String referenceNumber;
    private String transactionType;
    private String transactionStatus;
    private String transactionSource;
    private String paymentMode;
    private String transactionDate;
}
