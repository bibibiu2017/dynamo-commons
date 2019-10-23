package ke.co.dynamodigital.commons.models.whatsapp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bibibiu
 * created 9/3/19 at 00:31
 * Clickatell's message response Object
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MessageResponse {
    private String messageId;
    private boolean accepted;
    private String to;
    private String error;
    private String clientMessageId;
}
