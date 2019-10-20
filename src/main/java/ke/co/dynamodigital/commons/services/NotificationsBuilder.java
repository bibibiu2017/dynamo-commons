package ke.co.dynamodigital.commons.services;

import ke.co.dynamodigital.commons.models.notifications.Notification;

import java.util.Map;

/**
 * @author Bibibiu
 * created 9/3/19 at 15:39
 * This class defines all notification methods required to
 * create and interact with notifications
 **/
public interface NotificationsBuilder {

    /**
     * Notification builder that builds a notification
     * given a payload of type P and map containing metadata
     *
     * @param payload  notification payload
     * @param metadata metadata to extract notification info
     * @return returns built notification
     */
    <T> Notification buildNotification(T payload, Map<String, Object> metadata);
}
