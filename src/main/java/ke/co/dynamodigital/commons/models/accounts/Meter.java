package ke.co.dynamodigital.commons.models.accounts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author Bibibiu
 * created 8/29/19 at 19:07
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Meter {
    private String meterNumber;
    private String status;
    private Map<String,Object> additionalInfo;
}
