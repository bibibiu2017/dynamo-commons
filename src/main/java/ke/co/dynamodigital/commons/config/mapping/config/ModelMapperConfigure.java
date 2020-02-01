package ke.co.dynamodigital.commons.config.mapping.config;

import com.github.rozidan.springboot.modelmapper.ConfigurationConfigurer;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Conditions;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Component;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

/**
 * @author Bibibiu
 * created 10/14/19 at 11:40
 **/
@Slf4j
@Component
public class ModelMapperConfigure extends ConfigurationConfigurer {
    @Override
    public void configure(Configuration configuration) {
        configuration.setMatchingStrategy(STRICT);
        configuration.setSkipNullEnabled(true);
        configuration.setPropertyCondition(Conditions.isNotNull());
    }
}
