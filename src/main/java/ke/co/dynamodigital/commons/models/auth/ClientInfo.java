package ke.co.dynamodigital.commons.models.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

/**
 * @author Bibibiu
 * created 9/13/19 at 03:25
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ClientInfo {
    private String clientName;
    private String created;
    private String lastUsed;
    private String status;
    private String apiEnvironment;
    private String apiSubscription;
    private String callBackUrl;
    private String organization;
}
