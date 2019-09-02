package ke.co.dynamodigital.commons.models.notifications;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

/**
 * @author Bibibiu
 * created 9/2/19 at 23:20
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TokenModel {
    @Column(nullable = false)
    private String token;

    private String units;

    private String tariff;

    private String amount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Africa/Nairobi")
    private String timestamp;

    private String meterNumber;
}
