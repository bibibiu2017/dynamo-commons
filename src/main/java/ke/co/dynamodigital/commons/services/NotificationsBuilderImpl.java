package ke.co.dynamodigital.commons.services;

import com.google.common.collect.Lists;
import ke.co.dynamodigital.commons.models.notifications.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static ke.co.dynamodigital.commons.models.notifications.EgressType.SLACK;

/**
 * @author Bibibiu
 * created 9/3/19 at 16:03
 **/
@Slf4j
@Service
public abstract class NotificationsBuilderImpl<T extends Notification, P> implements NotificationsBuilder<T, P> {

    @Override
    public T buildNotification(P payload, Map<String, Object> metadata) {
        T notification = buildNotification(payload);
        notification.setRecipients(extractRecipients(metadata));
        notification.setRoutes(extractEgress(metadata));
        return notification;
    }

    protected List<EgressType> extractEgress(Map<String,Object> metadata) {
        log.warn("===Egress not extracted will user default egress: {}===", SLACK);
        return Lists.newArrayList(SLACK);
    }

    protected List<Recipient> extractRecipients(Map<String,Object> metadata) {

        Contact contact = Contact.builder()
                .slackWebHook((String) metadata.get("slack"))
                .phoneNumber((String) metadata.get("phoneNumber"))
                .email((String) metadata.get("email"))
                .callbacks((Map<String, String>) metadata.get("callbacks"))
                .build();
        return Lists.newArrayList(Recipient.builder().contact(contact).build());
    }

    protected abstract T buildNotification(P payload);
}
