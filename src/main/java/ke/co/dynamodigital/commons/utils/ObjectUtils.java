package ke.co.dynamodigital.commons.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author Bibibiu
 * created 8/28/19 at 18:34
 **/
@Slf4j
public class ObjectUtils {
    public static final ZoneId EAT_ZONE = ZoneId.of(ZoneId.SHORT_IDS.get("EAT"));
    private final static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.registerModules(new JavaTimeModule());
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

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
        public static <T> T readJson(String jsonString, Class<T> valueType) {
            return objectMapper.readValue(jsonString, valueType);
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
    @Deprecated
    public static <T> T jsonToObject(String jsonString, Class<T> valueType) {
        return objectMapper.readValue(jsonString, valueType);
    }

    /**
     * Gets a zoned date time formatter --- yyyy-MM-dd HH:mm:ss Z
     *
     * @return zoned date formatter
     */
    public static DateTimeFormatter zonedDf() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");
    }

    /**
     * Gets date formatter --- yyyy-MM-dd HH:mm:ss
     *
     * @return date formatter without zone
     */
    public static DateTimeFormatter df() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public static LocalDateTime nowEAT() {
        return LocalDateTime.now(ZoneId.of("Africa/Nairobi"));
    }
}
