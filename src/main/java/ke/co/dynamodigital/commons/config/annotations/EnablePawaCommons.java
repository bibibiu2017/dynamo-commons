package ke.co.dynamodigital.commons.config.annotations;


import ke.co.dynamodigital.commons.config.PawaCommonsConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({PawaCommonsConfiguration.class})
public @interface EnablePawaCommons {
}
