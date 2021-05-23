package ke.co.dynamodigital.commons.config.extension;

import com.github.rozidan.springboot.modelmapper.WithModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.*;

/**
 * @author arthurmita
 * created 26/05/2020 at 23:10
 **/

@SpringBootTest
@ContextConfiguration
@ActiveProfiles("test")
public abstract class WithMapper extends WithSoftAssertions {

    @Autowired
    protected ModelMapper modelMapper;

    @Configuration
    @WithModelMapper(basePackages = "ke.co.dynamodigital")
    public static class Mapper {

    }
}
