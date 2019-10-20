package ke.co.dynamodigital.commons.dtos.accounts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author Bibibiu
 * created 8/29/19 at 04:29
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDTO {
    private String refNumber;
    private String status;
    private Map<String, Object> results;
}
