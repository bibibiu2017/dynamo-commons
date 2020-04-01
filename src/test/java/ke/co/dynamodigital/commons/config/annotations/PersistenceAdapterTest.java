package ke.co.dynamodigital.commons.config.annotations;

import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SpringBootTest
@Tag("Persitence")
@ActiveProfiles("test")
public @interface PersistenceAdapterTest {
}
