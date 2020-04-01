package ke.co.dynamodigital.commons.mapping.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

/**
 * @author lawrencemwaniki
 * created 31/03/2020 at 18:30
 **/


@RequiredArgsConstructor
public class RecursiveConverter<S, D> implements Converter<S, D> {

    protected final ModelMapper modelMapper;

    @Override
    public D convert(MappingContext<S, D> context) {
        S source = context.getSource();
        return modelMapper.map(source, context.getDestinationType());
    }
}
