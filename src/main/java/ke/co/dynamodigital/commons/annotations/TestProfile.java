package ke.co.dynamodigital.commons.annotations;

import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author Bibibiu
 * created 10/22/19 at 16:35
 **/
@Documented
@Target(ElementType.TYPE)
@ActiveProfiles("test")
public @interface TestProfile {
}
