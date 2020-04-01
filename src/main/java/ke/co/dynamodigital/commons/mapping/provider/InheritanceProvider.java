package ke.co.dynamodigital.commons.mapping.provider;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.Provider;
import org.springframework.beans.BeanUtils;

/**
 * @author lawrencemwaniki
 * created 31/03/2020 at 17:32
 **/
@SuppressWarnings("unchecked")
@RequiredArgsConstructor
public class InheritanceProvider<S, D> implements Provider<D> {
    private final Class<? extends D> destinationChildClass;

    @SneakyThrows
    @Override
    public D get(ProvisionRequest<D> request) {
        S source = (S) request.getSource();
        D destination = destinationChildClass.getDeclaredConstructor().newInstance();
        BeanUtils.copyProperties(source, destination);
        return destination;
    }

}
