package ke.co.dynamodigital.commons.models.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

/**
 * @author arthurmita
 * created 15/06/2020 at 12:54
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class BaseResponseDTO {
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = STRING)
    private LocalDateTime createdOn;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = STRING)
    private LocalDateTime updatedOn;
}
