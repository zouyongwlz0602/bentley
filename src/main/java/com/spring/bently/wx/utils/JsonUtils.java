package com.spring.bently.wx.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wgq on 16-4-4.
 */
public class JsonUtils {

    //map-to-json
    public static String mapToJson(Map map) {
        if(map == null) {
            return null ;
        }

        ObjectMapper objectMapper = new ObjectMapper() ;
        try {
            String value = objectMapper.writeValueAsString(map) ;
            return value ;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null ;
    }

    //json-to-map
    public static Map jsonToMap(String json) {
        if(json == null) {
            return null ;
        }

        ObjectMapper objectMapper = new ObjectMapper() ;

        Map map = null ;
        try {
            map = objectMapper.readValue(json, HashMap.class) ;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map ;
    }

    //json-to-object
    public static Object jsonToObject(String json,Class object) {
        if(json == null) {
            return null ;
        }
        ObjectMapper objectMapper = new ObjectMapper() ;

        Object o = null ;
        try {
            o = objectMapper.readValue(json, object) ;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return o ;
    }

    //object-to-json
    public static String objectToJson(Object o) {
        if(o == null) {
            return null ;
        }
        ObjectMapper objectMapper = new ObjectMapper() ;
        try {
            String value = objectMapper.writeValueAsString(o) ;
            return value ;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null ;
    }
}
