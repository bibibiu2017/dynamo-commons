package ke.co.dynamodigital.commons.utils;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class AmqpUtilsTest {


    private SoftAssertions softly;

    @BeforeEach
    void setUp() {
        softly = new SoftAssertions();
    }

    @AfterEach
    void tearDown() {
        softly.assertAll();
    }

    @ParameterizedTest
    @CsvSource({
            "1,1024,0",
            "2,1024,2",
            "8,1024,128",
            "14,1024,1024",
    })
    void exponentialDelay(long failedAttempts,long maxDelay,long expectedDelay) {
        long actualDelay = AmqpUtils.exponentialDelay(failedAttempts,maxDelay);
        softly.assertThat(actualDelay).isCloseTo(expectedDelay, Offset.offset(1L));
    }
}
