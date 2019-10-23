package ke.co.dynamodigital.commons.annotations;


import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@ComponentScan(basePackages = "ke.co.dynamodigital.commons")
public @interface EnableCommons {
}
