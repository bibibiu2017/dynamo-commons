package ke.co.dynamodigital.commons.dtos.general;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author arthurmita
 * created 03/11/2019 at 23:04
 **/
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PageableResponseDTO<T> {
    private Integer page;
    private Integer rows;
    private Long totalRows;
    private Integer totalPages;
    private List<T> items;
}
