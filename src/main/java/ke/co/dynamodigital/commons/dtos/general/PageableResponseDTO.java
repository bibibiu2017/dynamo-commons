package ke.co.dynamodigital.commons.dtos.general;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Page;

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

    public static <T>PageableResponseDTO<T> from (Page<T> page){
        return PageableResponseDTO.<T>builder()
                .totalRows(page.getTotalElements())
                .items(page.getContent())
                .totalPages(page.getTotalPages())
                .page(page.getNumber())
                .rows(page.getNumberOfElements())
                .build();
    }
}
