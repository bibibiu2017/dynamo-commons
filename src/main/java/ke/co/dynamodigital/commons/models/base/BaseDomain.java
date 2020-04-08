package ke.co.dynamodigital.commons.models.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author arthurmita
 * created 08/04/2020 at 08:17
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class BaseDomain {
    private Long id;
    private Long version;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
