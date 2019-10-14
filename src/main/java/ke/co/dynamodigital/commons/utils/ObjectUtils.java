package ke.co.dynamodigital.commons.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author Bibibiu
 * created 8/28/19 at 18:34
 **/
@Slf4j
public class ObjectUtils {
    final static ObjectMapper objectMapper;
    public static final DateTimeFormatter df;
    public static final LocalDateTime now;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.registerModules(new JavaTimeModule());

        df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");

        now = LocalDateTime.now(ZoneId.of("Africa/Nairobi"));
    }

    /**
     * Method serializes object to json
     *
     * @param object object to serialize
     * @return json object
     */
    @SneakyThrows(IOException.class)
    public static String writeJson(Object object) {
        return objectMapper.writeValueAsString(object);
    }

    /**
     * Method to deserialize json object to a java pojo
     *
     * @param jsonString Json object to deserialize
     * @param valueType  expected Object type
     * @param <T>        Class type
     * @return java pojo
     */
    @SneakyThrows
    public static <T> T jsonToObject(String jsonString, Class<T> valueType) {
        return objectMapper.readValue(jsonString, valueType);
    }

    public static <T> T enumFromString(String enumName, Class<? extends Enum> enumType) {
        return ((T) Enum.valueOf(enumType, enumName));
    }

    public static final ZoneId EAT_ZONE = ZoneId.of(ZoneId.SHORT_IDS.get("EAT"));
}
