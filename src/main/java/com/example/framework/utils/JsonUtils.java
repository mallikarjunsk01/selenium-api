package com.example.framework.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class JsonUtils {
    public static JsonNode readJsonResource(String resourcePath) {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = JsonUtils.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) throw new RuntimeException("Resource not found: " + resourcePath);
            return mapper.readTree(is);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON: " + resourcePath, e);
        }
    }
}
