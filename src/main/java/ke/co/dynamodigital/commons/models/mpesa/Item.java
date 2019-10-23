package ke.co.dynamodigital.commons.models.mpesa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Bibibiu
 * created 2019-07-11 at 17:34
 **/
@Data
public class Item {
    @JsonProperty("Name")
    private String name;

    @JsonProperty("Value")
    private String Value;
}
