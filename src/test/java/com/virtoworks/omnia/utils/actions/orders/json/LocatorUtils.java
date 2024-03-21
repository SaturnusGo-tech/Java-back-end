package com.virtoworks.omnia.utils.actions.orders.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;

public class LocatorUtils {
    private static Map<String, Map<String, Map<String, Integer>>> data;
    private static Map<String, String> locators;

    public static void loadData() {
        if (data == null || locators == null) {
            Type type = new TypeToken<Map<String, Map<String, Map<String, Integer>>>>() {}.getType();
            data = new Gson().fromJson(new InputStreamReader(
                    Objects.requireNonNull(
                            LocatorUtils.class.getClassLoader().getResourceAsStream("jsonData/pageOrderActions/PageOrderSearchCheckBoxes.json"))), type);

            Type locatorsType = new TypeToken<Map<String, String>>() {}.getType();
            locators = new Gson().fromJson(new InputStreamReader(
                    Objects.requireNonNull(
                            LocatorUtils.class.getClassLoader().getResourceAsStream("jsonData/pageOrderActions/PageOrderSearchCheckBoxes.json"))), locatorsType);
        }
    }

    public static Map<String, String> getLocators() {
        if (locators == null) {
            loadData();
        }
        return locators;
    }

    public static Map<String, Map<String, Integer>> getCheckboxData(String key) {
        if (data == null) {
            loadData();
        }
        return data.getOrDefault(key, null);
    }
}
