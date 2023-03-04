package com.flmelody.cloud.util;

import com.google.gson.Gson;


/**
 * json utils implemented by gson
 *
 * @author flmelody
 */
public final class GsonUtils {
    static Gson gson = new Gson();

    /**
     * serialize object to json
     *
     * @param obj object
     * @return serialized json string
     */
    public static String toJson(Object obj) {
        return gson.toJson(obj);
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
        return gson.fromJson(json, clazz);
    }
}
