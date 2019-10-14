package ke.co.dynamodigital.commons.models.accounts;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.h2.mvstore.tx.Transaction;

import java.math.BigDecimal;

/**
 * @author Bibibiu
 * created 9/10/19 at 20:19
 **/
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TransactionTokenPurchaseModel extends TransactionModel {
    private String amount;
    private String meterNumber;
    private String phoneNumber;
}
