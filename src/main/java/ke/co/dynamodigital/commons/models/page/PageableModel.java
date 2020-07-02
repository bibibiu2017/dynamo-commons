package ke.co.dynamodigital.commons.models.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author arthurmita
 * created 03/11/2019 at 23:04
 **/
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PageableModel<T> implements Serializable {
    private Integer page;
    private Integer rows;
    private Long totalRows;
    private Integer totalPages;
    private List<T> items;

    public static <T> PageableModel<T> from(Page<T> page) {
        return PageableModel.<T>builder()
                .totalRows(page.getTotalElements())
                .items(page.getContent())
                .totalPages(page.getTotalPages())
                .page(page.getNumber())
                .rows(page.getNumberOfElements())
                .build();
    }

    public <S> PageableModel<S> map(Function<? super T, ? extends S> converter) {
        List<S> items = this.items.stream().map(converter).collect(Collectors.toList());
        return PageableModel.<S>builder()
                .totalRows(this.totalRows)
                .rows(this.rows)
                .page(this.page)
                .totalPages(this.totalPages)
                .items(items)
                .build();
    }
}
