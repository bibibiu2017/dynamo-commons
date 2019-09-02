package ke.co.dynamodigital.commons.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.ZoneId;

/**
 * @author Bibibiu
 * created 8/28/19 at 18:34
 **/
@Slf4j
public class ObjectUtils {
    final static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
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
     * @throws IOException exception
     */
    public static <T> T jsonToObject(String jsonString, Class<T> valueType) throws IOException {
        return objectMapper.readValue(jsonString, valueType);
    }

    public static final ZoneId EAT_ZONE = ZoneId.of(ZoneId.SHORT_IDS.get("EAT"));
}
