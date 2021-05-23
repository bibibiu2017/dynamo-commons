package ke.co.dynamodigital.commons.config.annotations;

import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

/**
 * @author arthurmita
 * created 07/01/2021 at 11:16
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Tag("UseCase")
@SpringBootTest
@ActiveProfiles
public @interface OutputPortTest {
    @AliasFor(
            annotation = SpringBootTest.class,
            attribute = "classes"
    )
    Class<?>[] classes() default {};

    @AliasFor(
            annotation = ActiveProfiles.class,
            attribute = "profiles"
    )
    String[] profiles() default {"test"};
}
