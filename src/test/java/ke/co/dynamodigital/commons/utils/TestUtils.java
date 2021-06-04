package ke.co.dynamodigital.commons.utils;

import com.github.tomakehurst.wiremock.http.HttpHeader;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.messaging.Message;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.*;

/**
 * @author arthurmita
 * created 07/01/2021 at 12:54
 **/
@Slf4j
@UtilityClass
public class TestUtils {

    public static final String USER_ROLE = "USER";
    public static final String ADMIN_ROLE = "ADMIN";
    public static final String SUPER_ADMIN_ROLE = "SUPER_ADMIN";
    public static final HttpHeader XML_CONTENT = HttpHeader.httpHeader(CONTENT_TYPE, APPLICATION_XML_VALUE);
    public HttpHeader JSON_CONTENT = new HttpHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE);

    public <T> T extractMessage(Message<byte[]> message, Class<T> type) {
        return ObjectUtils.readJson(new String(message.getPayload()), type);
    }

    public boolean isEqual(LocalDateTime value, LocalDateTime ref) {
        return value.truncatedTo(ChronoUnit.SECONDS).equals(ref.truncatedTo(ChronoUnit.SECONDS));
    }

    public int compareTo(LocalDateTime value, LocalDateTime ref) {
        if (isEqual(value, ref))
            return 0;
        else
            return -1;
    }

    public void quenchQueues(OutputDestination destination, String... queues) {
        for (String queue : queues) {
            try {
                Message<?> message;
                do {
                    message = destination.receive(5, queue);
                } while (Objects.nonNull(message));
            } catch (NullPointerException e) {
                LogUtils.logError(log, "Clear Queues Error", "Destination(" + queue + ") does not exist", e);
            }
        }
    }

    @UtilityClass
    public class RecursiveComparison {
        public static final RecursiveComparisonConfiguration DEFAULT = RecursiveComparisonConfiguration.builder()
                .withComparatorForType(TestUtils::compareTo, LocalDateTime.class)
                .withComparatorForType(BigDecimal::compareTo, BigDecimal.class)
                .build();
    }
}
