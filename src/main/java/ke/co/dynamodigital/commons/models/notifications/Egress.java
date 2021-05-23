package ke.co.dynamodigital.commons.models.notifications;

import lombok.*;

import java.util.*;

/**
 * @author Bibibiu
 * created 10/19/19 at 23:45
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Egress {
    EgressType type;

    @Singular
    List<Recipient> recipients = new ArrayList<>();

    public Egress addRecipient(Recipient recipient) {
        this.recipients.add(recipient);
        return this;
    }
}
