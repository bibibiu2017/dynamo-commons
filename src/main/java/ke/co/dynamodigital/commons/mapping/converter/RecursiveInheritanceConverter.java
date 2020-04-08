package ke.co.dynamodigital.commons.mapping.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

/**
 * @author lawrencemwaniki
 * created 31/03/2020 at 19:13
 **/
public class RecursiveInheritanceConverter<S, D> extends RecursiveConverter<S, D> {

    protected final Class<D> destinationSuperClazz;

    public RecursiveInheritanceConverter(ModelMapper modelMapper, Class<D> destinationSuperClazz) {
        super(modelMapper);
        this.destinationSuperClazz = destinationSuperClazz;
    }

    @Override
    public D convert(MappingContext<S, D> context) {
        S source = context.getSource();
        return modelMapper.map(source, destinationSuperClazz);
    }
}
