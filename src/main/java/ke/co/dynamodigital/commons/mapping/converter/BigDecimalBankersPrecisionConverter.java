package ke.co.dynamodigital.commons.mapping.converter;

import com.github.rozidan.springboot.modelmapper.ConverterConfigurer;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author lawrence
 * created 16/01/2020 at 10:16
 **/
@Component
public class BigDecimalBankersPrecisionConverter extends ConverterConfigurer<BigDecimal, BigDecimal> {
    @Override
    public Converter<BigDecimal, BigDecimal> converter() {
        return new AbstractConverter<>() {
            @Override
            protected BigDecimal convert(BigDecimal source) {
                return source.setScale(2, RoundingMode.HALF_EVEN);
            }
        };
    }
}
