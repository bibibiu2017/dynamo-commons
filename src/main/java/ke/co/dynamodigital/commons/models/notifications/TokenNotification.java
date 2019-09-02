package ke.co.dynamodigital.commons.models.notifications;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

/**
 * @author Bibibiu
 * created 9/2/19 at 23:14
 **/
@Data
public class TokenNotification extends Notification {
    private TokenModel tokenModel;

    @Builder(toBuilder = true)
    public TokenNotification(@Singular List<EgressType> routes, @Singular List<Recipient> recipients, TokenModel tokenModel) {
        super(routes, recipients);
        this.tokenModel = tokenModel;
    }
}
