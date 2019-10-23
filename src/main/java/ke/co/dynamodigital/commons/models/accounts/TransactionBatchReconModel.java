package ke.co.dynamodigital.commons.models.accounts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Bibibiu
 * created 10/10/19 at 03:21
 **/
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TransactionBatchReconModel extends TransactionModel {
    private String amount;
    private String phoneNumber;
    private String meterNumber;
    private String mpesaRefNumber;
}
