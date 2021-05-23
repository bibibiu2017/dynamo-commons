package ke.co.dynamodigital.commons.config.annotations;

import com.github.rozidan.springboot.modelmapper.*;
import ke.co.dynamodigital.commons.mapping.config.AbstractMapper;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author arthurmita
 * created 23/05/2021 at 15:03
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ImportAutoConfiguration({ModelMapperAutoConfiguration.class})
@ComponentScan(
        useDefaultFilters = false,
        includeFilters = {@ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = {TypeMapConfigurer.class, ConverterConfigurer.class, ConfigurationConfigurer.class, AbstractMapper.class}
        )}
)
public @interface WithModelMapper {
    @AliasFor(
            annotation = ComponentScan.class
    )
    String[] basePackages() default {};

    @AliasFor(
            annotation = ComponentScan.class
    )
    Class<?>[] basePackageClasses() default {};
}