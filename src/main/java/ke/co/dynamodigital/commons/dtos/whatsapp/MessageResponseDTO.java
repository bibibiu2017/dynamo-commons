package ke.co.dynamodigital.commons.dtos.whatsapp;

import ke.co.dynamodigital.commons.models.whatsapp.MessageResponse;
import lombok.*;

import java.util.List;

/**
 * @author Bibibiu
 * created 9/3/19 at 00:36
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MessageResponseDTO {
    @Singular
    private List<MessageResponse> messages;
    private String error;
}
