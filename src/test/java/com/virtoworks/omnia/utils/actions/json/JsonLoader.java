package com.virtoworks.omnia.utils.actions.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Map;

public class JsonLoader {

    private final Gson gson;

    public JsonLoader() {
        this.gson = new Gson();
    }

    public Map<String, Map<String, String>> loadJsonData(String resourcePath, Class<?> loadingClass) throws FileNotFoundException {
        Type simpleType = new TypeToken<Map<String, Map<String, String>>>() {}.getType();
        return loadJson(resourcePath, simpleType, loadingClass);
    }

    public Map<String, Map<String, Map<String, Integer>>> loadNestedJsonData(String resourcePath, Class<?> loadingClass) throws FileNotFoundException {
        Type nestedType = new TypeToken<Map<String, Map<String, Map<String, Integer>>>>() {}.getType();
        return loadJson(resourcePath, nestedType, loadingClass);
    }

    private <T> T loadJson(String resourcePath, Type type, Class<?> loadingClass) throws FileNotFoundException {
        InputStream inputStream = loadingClass.getClassLoader().getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new FileNotFoundException("JSON file not found at " + resourcePath);
        }
        InputStreamReader reader = new InputStreamReader(inputStream);
        return gson.fromJson(reader, type);
    }
}
