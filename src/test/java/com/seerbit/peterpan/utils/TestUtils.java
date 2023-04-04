package com.seerbit.peterpan.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {
    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper myObjectMapper = new ObjectMapper();
            myObjectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            String resp = myObjectMapper.writeValueAsString(obj);
            return resp;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
