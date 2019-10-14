package ke.co.dynamodigital.commons.configs.mapping.converters;

import com.github.rozidan.springboot.modelmapper.ConverterConfigurer;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Bibibiu
 * created 10/9/19 at 21:44
 **/
@Component
public class StringLocalDateConverter extends ConverterConfigurer<String, LocalDateTime> {
    @Override
    public Converter<String, LocalDateTime> converter() {
        return new AbstractConverter<String, LocalDateTime>() {
            @Override
            protected LocalDateTime convert(String date) {
                return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            }
        };
    }
}
