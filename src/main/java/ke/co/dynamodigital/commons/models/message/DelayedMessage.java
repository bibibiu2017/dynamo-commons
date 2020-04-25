package ke.co.dynamodigital.commons.models.message;

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
public class DelayedMessage<T> {

    @NotNull
    private T payload;

    @Nullable
    private Integer retires;

    @NotNull
    private String address;

    @Nullable
    protected Integer delay;

    @Nullable
    private MessageHeaders messageHeaders;

    public static <T> DelayedMessage<T> from(T payload, String address, Integer retires, Integer delay) {
        return DelayedMessage.<T>builder()
                .payload(payload)
                .address(address)
                .retires(retires)
                .delay(delay)
                .build();
    }

    public static <T> DelayedMessage<T> from(T payload, String address, Integer retires) {
        return DelayedMessage.from(payload, address, retires, 1000);
    }
}
