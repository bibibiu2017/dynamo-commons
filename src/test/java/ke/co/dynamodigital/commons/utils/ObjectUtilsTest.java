package ke.co.dynamodigital.commons.utils;

import ke.co.dynamodigital.commons.models.notifications.EgressType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class ObjectUtilsTest {

    @Test
    void enumFromString() {
        //when
        EgressType egressType = ObjectUtils.enumFromString("SMS",EgressType.class);

        //then
        assertThat(egressType,is(EgressType.SMS));
    }
}
