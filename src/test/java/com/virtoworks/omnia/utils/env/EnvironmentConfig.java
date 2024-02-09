package com.virtoworks.omnia.utils.env;

import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.InputStream;

public class EnvironmentConfig {

    private final String url;
    private final String email;
    private final String password;

    private final String getEnvDuration;

    public EnvironmentConfig(String envName) {
        InputStream is = getClass().getClassLoader().getResourceAsStream("config.json");
        if (is == null) {
            throw new RuntimeException("Cannot find 'config.json' file in the resources");
        }
        JSONObject jsonObject = new JSONObject(new JSONTokener(is));
        JSONObject envConfig = jsonObject.getJSONObject(envName);

        this.url = envConfig.getString("url");
        this.email = envConfig.getString("email");
        this.password = envConfig.getString("password");
        this.getEnvDuration = envConfig.getString("envDuration");
    }

    public String getUrl() {
        return url;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getEnvDuration() {

        return (Integer) Integer.parseInt(getEnvDuration);
    }
}
