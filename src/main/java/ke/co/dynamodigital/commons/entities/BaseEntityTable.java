package ke.co.dynamodigital.commons.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author Bibibiu
 * created 8/28/19 at 08:51
 **/
@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class BaseEntityTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
}
