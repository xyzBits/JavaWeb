package com.dongfang.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class JsonUtils {
    public static JsonNode parseInputToJson(InputStream is) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(is);
    }

    public static ObjectMapper getMapper() {
        return new ObjectMapper();
    }
}
