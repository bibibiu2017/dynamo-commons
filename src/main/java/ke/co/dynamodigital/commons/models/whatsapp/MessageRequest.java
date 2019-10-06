package ke.co.dynamodigital.commons.models.whatsapp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bibibiu
 * created 9/3/19 at 00:25
 * Clickatell's message request Object
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MessageRequest {
    private String to;
    private String content;
}
