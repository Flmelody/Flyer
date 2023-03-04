package com.flmelody.cloud.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flmelody.cloud.common.exception.FlyerException;



/**
 * json utils implemented by jackson
 *
 * @author flmelody
 */
public final class JacksonUtils {
    static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * serialize object to json
     *
     * @param obj object
     * @return serialized json string
     */
    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new FlyerException();
        }
    }

    /**
     * deserialize json to object
     *
     * @param json  json string
     * @param clazz objects class
     * @param <T>   general type
     * @return deserialized object
     */
    public static <T> T toObj(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json,clazz);
        } catch (JsonProcessingException e) {
            throw new FlyerException();
        }
    }
}
