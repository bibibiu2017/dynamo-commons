package ke.co.dynamodigital.commons.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import ke.co.dynamodigital.commons.config.extension.WithSoftAssertions;
import ke.co.dynamodigital.commons.models.notification.Recipient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

@Slf4j
public class ObjectUtilsTest extends WithSoftAssertions {

    Recipient recipient;
    String recipientMapJson;
    String recipientListJson;

    @BeforeEach
    void setUp() {
        recipient = Recipient.builder().address("254709279000").build();
        LogUtils.logObject(log, Map.of("1", recipient));
        recipientListJson = "[ {\n" +
                "  \"address\" : \"254709279000\"\n" +
                "} ]";
        recipientMapJson = "{\n" +
                "  \"1\" : {\n" +
                "    \"address\" : \"254709279000\"\n" +
                "  }\n" +
                "}";
    }

    @Test
    @DisplayName("should map json to object")
    void shouldMapJsonToObject() {
        //given:
        var source = recipientListJson;

        //when:
        List<Recipient> recipients = ObjectUtils.readJson(source, new TypeReference<>() {
        });

        //then:
        softly.assertThat(recipients).isNotEmpty().containsOnly(recipient);
    }

    @Test
    @DisplayName("should map json to map")
    void shouldMapJsonToMap() {
        //given:
        var source = recipientMapJson;

        //when:
        Map<String, Recipient> recipients = ObjectUtils.readJson(source, new TypeReference<>() {
        });

        //then:
        softly.assertThat(recipients).containsOnlyKeys("1")
                .extractingByKey("1")
                .satisfies(recipient1 -> softly.assertThat(recipient1).isEqualTo(recipient));
        LogUtils.logObject(log, recipients);
    }
}