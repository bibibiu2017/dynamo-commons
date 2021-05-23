package ke.co.dynamodigital.commons.config.annotations;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.*;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.stream.Stream;

/**
 * @author arthurmita
 * created 02/05/2021 at 17:06
 **/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ArgumentsSource(VariableSource.VariableArgumentsProvider.class)
public @interface VariableSource {
    /**
     * The name of the static variable
     */
    String value();

    class VariableArgumentsProvider implements ArgumentsProvider, AnnotationConsumer<VariableSource> {

        private String variableName;

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            var clazz = context.getTestClass();
            return context.getTestClass()
                    .map(this::getField)
                    .map(this::getValue)
                    .orElseThrow(() ->
                            new IllegalArgumentException("Failed to load test arguments"));
        }

        @Override
        public void accept(VariableSource variableSource) {
            variableName = variableSource.value();
        }

        private Field getField(Class<?> clazz) {
            try {
                return clazz.getDeclaredField(variableName);
            } catch (Exception e) {
                return null;
            }
        }

        @SuppressWarnings("unchecked")
        private Stream<Arguments> getValue(Field field) {
            Object value = null;
            try {
                field.setAccessible(true);
                value = field.get(null);
            } catch (Exception ignored) {
            }

            return value == null ? null : (Stream<Arguments>) value;
        }
    }
}
