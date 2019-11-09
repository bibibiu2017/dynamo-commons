package ke.co.dynamodigital.commons.config.mapping.converters;

import com.github.rozidan.springboot.modelmapper.ConverterConfigurer;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Bibibiu
 * created 10/9/19 at 21:29
 **/
@Slf4j
@Component
public class LocaleDateTimeStringConverter extends ConverterConfigurer<LocalDateTime, String> {

    @Override
    public Converter<LocalDateTime, String> converter() {
        return new AbstractConverter<LocalDateTime, String>() {
            @Override
            protected String convert(LocalDateTime localDateTime) {
                return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            }
        };
    }
}
