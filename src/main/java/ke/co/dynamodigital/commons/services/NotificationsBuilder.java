package ke.co.dynamodigital.commons.services;

import ke.co.dynamodigital.commons.models.notifications.Notification;

import java.util.List;
import java.util.Map;

/**
 * @author Bibibiu
 * created 9/3/19 at 15:39
 * This class defines all notification methods required to
 * create and interact with notifications
 **/
public interface NotificationsBuilder<T extends Notification, P> {

    /**
     * Notification builder that builds a notification of type T
     * give a payload of type P and map containing metadata
     * @param payload  notification payload
     * @param metadata metadata to extract notification info
     * @return Notification of type T
     */
    T buildNotification(P payload, Map<String, Object> metadata);
}
