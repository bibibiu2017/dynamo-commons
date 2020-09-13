package ke.co.dynamodigital.commons.models.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class BaseResponse {
    @Schema(description = "Unique ID", example = "94909162-096c-43cf-a5e0-b734eca302f8")
    private String id;

    @Schema(description = "Creation timestamp", example = "2020-02-02 19:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = STRING)
    private LocalDateTime createdOn;

    @Schema(description = "Last update timestamp", example = "2020-02-02 19:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = STRING)
    private LocalDateTime updatedOn;
}
