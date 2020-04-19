package ke.co.dynamodigital.commons.models.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author arthurmita
 * created 19/04/2020 at 10:55
 **/
@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "created_on", columnDefinition = "DATETIME", updatable = false)
    private LocalDateTime createdOn;

    @Column(name = "updated_on", columnDefinition = "DATETIME")
    private LocalDateTime updatedOn;
}