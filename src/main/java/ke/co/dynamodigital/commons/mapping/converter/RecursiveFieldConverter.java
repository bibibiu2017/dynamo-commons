package ke.co.dynamodigital.commons.mapping.converter;

import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author arthurmita
 * created 01/04/2020 at 22:15
 **/
public class RecursiveFieldConverter<S, D> extends RecursiveConverter<S, D> {

    private final Map<Type, String> fieldTypes;
    private final Class<? extends D> destinationClazz;

    public RecursiveFieldConverter(ModelMapper modelMapper, Class<? extends D> destinationClazz, Map<Type, String> fieldTypes) {
        super(modelMapper);
        this.fieldTypes = fieldTypes;
        this.destinationClazz = destinationClazz;
    }

    @Override
    @SneakyThrows
    public D convert(MappingContext<S, D> context) {
        S source = context.getSource();
        D destination = context.getDestination();


        for (Entry<Type, String> fieldTypes : this.fieldTypes.entrySet()) {
            Type fieldType = fieldTypes.getKey();
            String fieldName = fieldTypes.getValue();

            Field sourceField = ReflectionUtils.findField(context.getSourceType(), fieldName);
            assert sourceField != null;
            sourceField.setAccessible(true);
            Object sourceValue = sourceField.get(source);

            Field destinationField = ReflectionUtils.findField(this.destinationClazz, fieldName);
            assert destinationField != null;
            destinationField.setAccessible(true);

            Object destinationValue = modelMapper.map(sourceValue, fieldType);
            destinationField.set(destination, destinationValue);
        }


        return destination;
    }
}
