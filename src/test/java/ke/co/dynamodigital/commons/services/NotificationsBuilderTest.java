package ke.co.dynamodigital.commons.services;

import com.google.common.collect.Lists;
import ke.co.dynamodigital.commons.configs.bean.NotificationsBuilderAutoConfigurer;
import ke.co.dynamodigital.commons.models.notifications.*;
import ke.co.dynamodigital.commons.testutils.TokenNotification;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ke.co.dynamodigital.commons.utils.ObjectUtils.writeJson;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;

@Slf4j
@Tag("service")
@SpringBootTest
@ContextConfiguration(classes = NotificationsBuilderAutoConfigurer.class)
class NotificationsBuilderTest {

    private static TokenNotification tokenNotification;
    private static Map<String, Object> metadata;

    @Autowired
    private NotificationsBuilderImpl notificationsBuilder;

    @BeforeAll
    static void setUpAll() {
        tokenNotification = TokenNotification.builder()
                .amount("100")
                .meterNumber("14140384281")
                .refNumber("WA-1234567890-TOK-1010")
                .token("1234 5678 9012 3456 7890")
                .tariff("Domestic Lifeline")
                .timestamp("2019-10-20 02:02:00")
                .units("6.53")
                .messageType(MessageType.DELAYED)
                .build();
        metadata = new HashMap<String, Object>() {{
            put(EgressType.WHATSAPP.name(), Lists.newArrayList(
                    Recipient.builder().address("254711993939").build(),
                    Recipient.builder().address("254712347952").build())
            );
            put(EgressType.CALLBACK.name(), Lists.newArrayList(
                    Recipient.builder().address("https://api.dynamodigital-ke.com/payment/v1/callback/momo"))
            );
            put("type", NotificationType.TOKEN.name());
        }};

    }

    @Test
    void buildNotification() {
        //when
        val notification = notificationsBuilder.buildNotification(tokenNotification, metadata);
        val recipients = notification.getEgresses();
        //then
        assertThat(notification, is(notNullValue(Notification.class)));
        log.debug("\n===================\nNotification: {}\n======================", writeJson(notification));
        assertThat(notification, allOf(
                hasProperty("notificationType", is(NotificationType.TOKEN)),
                hasProperty("egresses", isA(List.class)),
                hasProperty("requirements", isA(Map.class))
        ));
        assertThat(recipients, hasSize(2));
    }

    @Test
    void extractEgress() {
        //when
        List<Egress> egresses = notificationsBuilder.extractEgress(metadata);

        //then
        assertThat(egresses, is(notNullValue(List.class)));
        log.debug("\n================\nEgresses: {}\n====================", writeJson(egresses));
    }

    @Test
    void extractType() {
        //when
        NotificationType type = notificationsBuilder.extractType(metadata);

        //then
        assertThat(type, is(notNullValue(NotificationType.class)));
        assertThat(type, is(NotificationType.TOKEN));
    }

    @Test
    void extractRequirements() {
        //when
        Map<String, String> requirements = notificationsBuilder.extractRequirements(tokenNotification);

        //then
        log.debug("\n===========Requirements: {}\n==================", writeJson(requirements));
        assertThat(requirements, is(notNullValue(Map.class)));
    }
}
