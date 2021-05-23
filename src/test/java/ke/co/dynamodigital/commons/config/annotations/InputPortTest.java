package ke.co.dynamodigital.commons.config.annotations;

import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.*;

import java.lang.annotation.*;

/**
 * @author arthurmita
 * created 07/01/2021 at 11:15
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Tag("UseCase")
@SpringBootTest
@ActiveProfiles
@ContextConfiguration
@Import({TestChannelBinderConfiguration.class})
public @interface InputPortTest {
    @AliasFor(
            annotation = ContextConfiguration.class,
            attribute = "classes"
    )
    Class<?>[] classes() default {};

    @AliasFor(
            annotation = SpringBootTest.class,
            attribute = "properties"
    )
    String[] properties() default {};

    @AliasFor(
            annotation = ActiveProfiles.class,
            attribute = "profiles"
    )
    String[] profiles() default {"test"};
}