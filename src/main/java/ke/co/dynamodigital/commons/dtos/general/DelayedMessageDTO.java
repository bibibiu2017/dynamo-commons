package ke.co.dynamodigital.commons.dtos.general;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.messaging.MessageHeaders;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * @author arthurmita
 * created 09/11/2019 at 18:47
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class DelayedMessageDTO<T> {

    @NotNull
    protected T payload;

    @Nullable
    protected Integer retires;

    @NotNull
    protected String address;

    @Nullable
    protected Integer delay;

    @Nullable
    protected MessageHeaders messageHeaders;

    public DelayedMessageDTO(T payload, String address) {
        this(payload,null,address,null,null);
    }
}
