package ke.co.dynamodigital.commons.mapping.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author arthurmita
 * created 26/06/2020 at 08:34
 **/
public abstract class AbstractMapper {

    protected ModelMapper modelMapper;

    @PostConstruct
    protected abstract void configure();

    @Autowired
    protected void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
