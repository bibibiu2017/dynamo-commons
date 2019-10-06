package ke.co.dynamodigital.commons.models.notifications;

import lombok.*;

import java.util.List;

/**
 * @author Bibibiu
 * created 9/3/19 at 20:01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
public class GenericNotification extends Notification {

    @Singular
    private List<String> notificationFields;
}
