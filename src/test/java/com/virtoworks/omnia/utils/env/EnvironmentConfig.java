package com.virtoworks.omnia.utils.env;

import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.InputStream;

public class EnvironmentConfig {

    private String url;
    private String email;
    private String password;

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
}
