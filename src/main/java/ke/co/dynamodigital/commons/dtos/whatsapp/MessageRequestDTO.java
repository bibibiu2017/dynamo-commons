package ke.co.dynamodigital.commons.dtos.whatsapp;

import ke.co.dynamodigital.commons.models.whatsapp.MessageRequest;
import lombok.*;

import java.util.List;

/**
 * @author Bibibiu
 * created 9/3/19 at 00:27
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MessageRequestDTO {
    @Singular
    private List<MessageRequest> messages;
}
