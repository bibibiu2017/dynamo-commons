package ke.co.dynamodigital.commons.models.mpesa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Bibibiu
 * created 2019-07-11 at 06:57
 **/
@Data
public class CallbackMetadata {

    @JsonProperty("Item")
    List<Item> items;
}
