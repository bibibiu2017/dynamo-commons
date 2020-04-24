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
public class DelayedMessageModel<T> {

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

    public DelayedMessageModel(T payload, String address) {
        this(payload,null,address,null,null);
    }
}
