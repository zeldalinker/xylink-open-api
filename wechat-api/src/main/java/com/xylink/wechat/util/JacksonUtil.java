package com.xylink.wechat.util;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;


public class JacksonUtil {

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(org.codehaus.jackson.JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(org.codehaus.jackson.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(org.codehaus.jackson.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(org.codehaus.jackson.JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(org.codehaus.jackson.JsonParser.Feature.INTERN_FIELD_NAMES, true);
        objectMapper.configure(org.codehaus.jackson.JsonParser.Feature.CANONICALIZE_FIELD_NAMES, true);
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.setDateFormat(dateFormat);
        //		objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
        //			@Override
        //			public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        //				jgen.writeString("");
        //			}
        //		});
    }

    public static ObjectMapper getObjectMapperInstance() {

        return objectMapper;
    }

    public static Map<String, Object> readJsontoMap(String json) {

        try {
            return objectMapper.readValue(json, Map.class);
        } catch (JsonParseException e) {
            return null;
        } catch (JsonMappingException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public static List<Object> readJsontoList(String json) {

        try {
            return objectMapper.readValue(json, List.class);
        } catch (JsonParseException e) {
            return null;
        } catch (JsonMappingException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public static <T> T readJsonFromFile(File src, Class<T> valueType) {

        try {
            return objectMapper.readValue(src, valueType);
        } catch (JsonParseException e) {
            return null;
        } catch (JsonMappingException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public static <T> T objectToBean(Object object, Class<T> cls) {

        try {
            return (T) objectMapper.readValue(toJson(object), cls);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static String writJson(Object object) {

        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonGenerationException e) {
            return null;
        } catch (JsonMappingException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public static String toJson(Object obj) {

        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T toBean(String json, Class<T> cls) {

        try {
            return (T) objectMapper.readValue(json, cls);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T objectToBean(Object object, TypeReference<T> type) {

        try {
            return (T) objectMapper.readValue(toJson(object), type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T toBean(String json, TypeReference<T> type) {

        try {
            return (T) objectMapper.readValue(json, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static <T> T fromJson(String json, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {

        return objectMapper.readValue(json, clazz);
    }

    public static List<?> getCollectionType(String json, Class<?> collectionClass, Class<?>... elementClasses)
        throws JsonParseException, JsonMappingException, IOException {

        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        return objectMapper.readValue(json, javaType);
    }

    public static void main(String[] args) throws Exception {

    }
}
