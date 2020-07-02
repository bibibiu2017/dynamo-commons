package ke.co.dynamodigital.commons.models.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author arthurmita
 * created 24/06/2020 at 22:19
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class BaseHash implements Serializable {

    private static final long serialVersionUID = 789065439L;

    @Id
    protected String key;

    @Setter(PRIVATE)
    protected boolean locked;

    @TimeToLive
    @JsonProperty("TTL")
    protected Long TTL;

    public boolean isUnlocked() {
        return !this.locked;
    }

    public void lock() {
        setLocked(true);
    }

    public void unlock() {
        setLocked(false);
    }
}

