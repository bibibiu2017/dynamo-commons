package ke.co.dynamodigital.commons.models.accounts;

import lombok.*;
import org.h2.mvstore.tx.Transaction;

import java.math.BigDecimal;

/**
 * @author Bibibiu
 * created 9/10/19 at 20:19
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TokenPurchaseModel extends TransactionModel {
    private String amount;
    private String meterNumber;
    private String phoneNumber;
}
