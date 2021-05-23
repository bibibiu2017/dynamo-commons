package ke.co.dynamodigital.commons.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author Bibibiu
 * created 8/28/19 at 18:34
 **/
@Slf4j
@UtilityClass
public class ObjectUtils {
    private final ObjectMapper objectMapper;

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
    public String writeJson(Object object) {
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
        public <T> T readJson(String jsonString, Class<T> valueType) {
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
    public <T> T jsonToObject(String jsonString, Class<T> valueType) {
        return objectMapper.readValue(jsonString, valueType);
    }

    /**
     * Get random ENUM value
     *
     * @param clazz target enum class
     * @param <T>   class type
     * @return found enum
     */
    public <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        SecureRandom random = new SecureRandom();
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    /**
     * Gets a zoned date time formatter --- yyyy-MM-dd HH:mm:ss Z
     *
     * @return zoned date formatter
     */
    public DateTimeFormatter zonedDf() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");
    }

    /**
     * Gets date formatter --- yyyy-MM-dd HH:mm:ss
     *
     * @return date formatter without zone
     */
    public DateTimeFormatter df() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public LocalDateTime nowEAT() {
        return LocalDateTime.now(ZoneId.of("Africa/Nairobi"));
    }
}
