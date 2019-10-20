package ke.co.dynamodigital.commons.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ke.co.dynamodigital.commons.models.notifications.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ke.co.dynamodigital.commons.models.notifications.EgressType.SLACK;

/**
 * @author Bibibiu
 * created 9/3/19 at 16:03
 **/
@SuppressWarnings("unchecked")
@Slf4j
public class NotificationsBuilderImpl<T> implements NotificationsBuilder {

    @Override
    public <T> Notification buildNotification(T payload, Map<String, Object> metadata) {
        return Notification.builder()
                .notificationType(extractType(metadata))
                .requirements(extractRequirements(payload))
                .egresses(extractEgress(metadata))
                .build();
    }

    /**
     * <p>
     * Extracts List of egress from metadata. For this to work, metadata
     * provided should contain maps of
     *     <ul>
     *         <li><strong>key</strong> -> egressType</li>
     *         <li><strong>value</strong> -> List(Recipients)</li>
     *     </ul>
     * </p>
     *
     * @param metadata metadata to extract egresses
     * @return List of egresses
     * @throws ClassCastException            when invalid metadata is provided
     * @throws NullPointerException          when invalid metadata is provided
     * @throws UnsupportedOperationException when invalid metadata is provided
     */
    protected List<Egress> extractEgress(Map<String, Object> metadata) throws ClassCastException, NullPointerException, UnsupportedOperationException {
        log.warn("===Egress not extracted will user default egress: {}===", SLACK);
        return Stream.of(EgressType.values())
                .map(egressType -> new HashMap<EgressType, List<Recipient>>() {{
                    put(egressType, (List<Recipient>) metadata.get(egressType.name()));
                }})
                .map(HashMap::entrySet)
                .flatMap(Collection::stream)
                .filter(entry -> entry.getKey() != null && entry.getValue() != null)
                .map(entry -> Egress.builder().type(entry.getKey()).recipients(entry.getValue()).build())
                .collect(Collectors.toList());
    }

    /**
     * <p>
     * Extracts the notification type from metadata map.
     * The type must be provided using:<br> <strong>key</strong> -> type<br>
     * The notification type must also be valid. Validity is determined
     * by accurate spelling otherwise it is case insensitive.
     * </p>
     *
     * @param metadata metadata to extract
     * @return Notification type
     * @see NotificationType
     */
    protected NotificationType extractType(Map<String, Object> metadata) throws IllegalArgumentException {
        String type = (String) metadata.get("type");
        if (type == null) {
            throw new IllegalArgumentException("Type was null");
        }
        return NotificationType.valueOf(type.toUpperCase());
    }

    /**
     * <p>
     * Converts payload object to notification requirements map.
     * Only works for simple objects with primitive fields.For
     * complex objects this class has to be overridden
     * </p>
     *
     * @param payload payload to build notification requirements
     * @return notification requirements
     * @throws IllegalArgumentException if invalid payload is passed
     */
    protected <T> Map<String, String> extractRequirements(T payload) throws IllegalArgumentException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(payload, Map.class);
    }
}
