package ke.co.dynamodigital.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class ComparableUtilsTest {

    @ParameterizedTest
    @CsvSource({
            "0.025,0.0250,true",
            "100.23,100,false",
            "100,100.0,true",
            "1000000.01234,1000000.01233,false",
            "0,0,true"
    })
    void isEqualTo(BigDecimal givenValue, BigDecimal ref, boolean expectedResult) {
        boolean actualResult = ComparableUtils.isEqualTo(givenValue, ref);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "0.025,1,false",
            "100.0000000001,100,true",
            "-100,100.0,false",
            "-256,-1000,true",
            "0,0,false"
    })
    void isGreaterThan(BigDecimal givenValue, BigDecimal ref, boolean expectedResult) {
        boolean actualResult = ComparableUtils.isGreaterThan(givenValue, ref);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "0.025,1,false",
            "100.0000000001,100,true",
            "-100,100.0,false",
            "-256,-1000,true",
            "0,0,true"
    })
    void isGreaterThanOrEqualTo(BigDecimal givenValue, BigDecimal ref, boolean expectedResult) {
        boolean actualResult = ComparableUtils.isGreaterThanOrEqualTo(givenValue, ref);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "0.025,1,true",
            "100.0000000001,100,false",
            "-100,100.0,true",
            "-256,-1000,false",
            "0,0,false"
    })
    void isLessThan(BigDecimal givenValue, BigDecimal ref, boolean expectedResult) {
        boolean actualResult = ComparableUtils.isLessThan(givenValue, ref);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "0.025,1,true",
            "100.0000000001,100,false",
            "-100,100.0,true",
            "-256,-1000,false",
            "0,0,true"
    })
    void isLessThanOrEqualTo(BigDecimal givenValue, BigDecimal ref, boolean expectedResult) {
        boolean actualResult = ComparableUtils.isLessThanOrEqualTo(givenValue, ref);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "100,50,200,true",
            "100,99,100,true",
            "0.0001,0,1,true",
            "1000,0,999.99999999,false",
            "-5,-10,-1,true"
    })
    void isBetween(BigDecimal givenValue, BigDecimal start, BigDecimal end, boolean expectedResult) {
        boolean actualResult = ComparableUtils.isBetween(givenValue, start, end);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "100,100,50",
            "-5,-1,-10",
            "0.5,0.7,0.3"
    })
    void isBetween_WillThrowIllegalArgumentException_WhenEndLessThanStart(BigDecimal givenValue, BigDecimal start, BigDecimal end) {
        assertThatThrownBy(() -> ComparableUtils.isBetween(givenValue, start, end))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("end must be greater than start");
    }
}
