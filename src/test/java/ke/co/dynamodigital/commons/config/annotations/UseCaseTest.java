package ke.co.dynamodigital.commons.config.annotations;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Tag("UseCase")
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public @interface UseCaseTest {
}
