package ke.co.dynamodigital.commons.config;

import com.github.rozidan.springboot.modelmapper.WithModelMapper;
import ke.co.dynamodigital.commons.error.GlobalExceptionHandlerController;
import ke.co.dynamodigital.commons.stream.MessageSenderImpl;
import ke.co.dynamodigital.commons.stream.ParkingStream;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author arthurmita
 * created 27/10/2019 at 12:37
 **/
@Configuration
@Import({
        MessageSenderImpl.class,
        ParkingStream.class,
        GlobalExceptionHandlerController.class
})
@WithModelMapper(basePackages = "ke.co.dynamodigital.commons")
public class PawaCommonsConfiguration {
}
