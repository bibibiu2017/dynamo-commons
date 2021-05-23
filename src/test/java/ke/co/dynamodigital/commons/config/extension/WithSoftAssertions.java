package ke.co.dynamodigital.commons.config.extension;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.*;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * @author arthurmita
 * created 04/06/2020 at 11:17
 **/
@ExtendWith(SoftAssertionsExtension.class)
public abstract class WithSoftAssertions extends WithMockito {

    @InjectSoftAssertions
    protected SoftAssertions softly;
}
