package ke.co.dynamodigital.commons.config.extension;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author arthurmita
 * created 30/03/2021 at 19:40
 **/
@ExtendWith(MockitoExtension.class)
abstract class WithMockito {
    @Captor
    protected ArgumentCaptor<?> captor;
}
