package ke.co.dynamodigital.commons.models.notifications;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Bibibiu
 * created 9/2/19 at 21:08
 * <strong>Note:</strong> For every egress type provided all recepients must have the required
 * egress address
 * a valid address
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TokenNotification.class,name = "tokenNotification"),
        @JsonSubTypes.Type(value = GenericNotification.class,name = "genericNotification"),
        @JsonSubTypes.Type(value = TransactionNotification.class,name = "transactionNotification")
})
public class Notification {
    List<EgressType> routes;
    List<Recipient> recipients;
    boolean delayed;
}
