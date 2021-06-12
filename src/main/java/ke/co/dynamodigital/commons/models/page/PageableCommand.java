package ke.co.dynamodigital.commons.models.page;

import lombok.Data;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.Objects;

import static java.lang.Integer.MAX_VALUE;
import static java.time.LocalDateTime.now;

/**
 * @author arthurmita
 * created 09/10/2020 at 15:14
 **/
@Data
public abstract class PageableCommand {
    protected LocalDateTime start;
    protected LocalDateTime end;
    protected Pageable pageable;

    protected PageableCommand(LocalDateTime start, LocalDateTime end, Integer page, Integer size) {
        this.start = Objects.isNull(start) ? now().minusMonths(6) : start;
        this.end = Objects.isNull(end) ? now() : end;
        this.pageable = PageRequest.of(
                Objects.isNull(page) ? 0 : page, Objects.isNull(size) ? MAX_VALUE : size, Sort.by("createdOn").descending()
        );
    }

    protected PageableCommand(LocalDateTime start, LocalDateTime end, Integer page, Integer size, Sort sort) {
        this.start = Objects.isNull(start) ? now().minusMonths(6) : start;
        this.end = Objects.isNull(end) ? now() : end;
        this.pageable = PageRequest.of(
                Objects.isNull(page) ? 0 : page, Objects.isNull(size) ? MAX_VALUE : size, sort
        );
    }
}
