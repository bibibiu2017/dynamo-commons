package ke.co.dynamodigital.commons.dtos.notifications;

import ke.co.dynamodigital.commons.models.notifications.EgressType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author arthurmita
 * created 02/11/2019 at 08:36
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequestDTO {
    private Long id;
    private EgressType route;
    private String address;
}
