package ke.co.dynamodigital.commons.models.page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import one.util.streamex.StreamEx;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author arthurmita
 * created 03/11/2019 at 23:04
 **/
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "PageResponse")
public class PageableModel<T> implements Serializable {
    @Schema(description = "Current page", example = "0", required = true)
    private Integer page;

    @Schema(description = "Rows received", example = "1", required = true)
    private Integer rows;

    @Schema(description = "Total rows available", example = "15", required = true)
    private Long totalRows;

    @Schema(description = "Total pages available", type = "int",example = "15", required = true)
    private Integer totalPages;

    @Schema(description = "Found items", required = true)
    private List<T> items;

    public static <T> PageableModel<T> from(Page<T> page) {
        return PageableModel.<T>builder()
                .page(page.getNumber())
                .items(page.getContent())
                .totalPages(page.getTotalPages())
                .rows(page.getNumberOfElements())
                .totalRows(page.getTotalElements())
                .build();
    }

    public <S> PageableModel<S> map(Function<? super T, ? extends S> converter) {
        List<S> items = this.items.stream().map(converter).collect(Collectors.toUnmodifiableList());
        return PageableModel.<S>builder()
                .items(items)
                .rows(this.rows)
                .page(this.page)
                .totalRows(this.totalRows)
                .totalPages(this.totalPages)
                .build();
    }

    public Stream<T> stream() {
        return StreamEx.of(this.items);
    }
}
