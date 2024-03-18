package com.virtoworks.omnia.utils.env;

import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.InputStream;

/**
 * Handles environment configuration settings.
 * This class loads configuration settings from a JSON file and provides access to these settings
 * such as URL, email, password, and duration through getter methods.
 */
public class EnvironmentConfig {

    private final String url;
    private final String email;
    private final String password;
    private final String getEnvDuration;

    /**
     * Constructs an EnvironmentConfig object by loading configuration settings from a JSON file.
     *
     * @param envName The name of the environment for which configuration settings are loaded.
     *                The environment name should match a key in the JSON configuration file.
     */
    public EnvironmentConfig(String envName) {
        // Load the configuration JSON file from the resources' directory.
        InputStream is = getClass().getClassLoader().getResourceAsStream("config.json");
        if (is == null) {
            throw new RuntimeException("Cannot find 'config.json' file in the resources");
        }
        JSONObject jsonObject = new JSONObject(new JSONTokener(is));
        // Retrieve the configuration settings for the specified environment.
        JSONObject envConfig = jsonObject.getJSONObject(envName);

        // Initialize the properties with the loaded configuration settings.
        this.url = envConfig.getString("url");
        this.email = envConfig.getString("email");
        this.password = envConfig.getString("password");
        this.getEnvDuration = envConfig.getString("envDuration");
    }

    // Getter method for URL
    public String getUrl() {
        return url;
    }

    // Getter method for email
    public String getEmail() {
        return email;
    }

    // Getter method for password
    public String getPassword() {
        return password;
    }

    /**
     * Getter method for environment duration.
     * Converts the duration from String to Integer.
     *
     * @return The environment-specific duration as an Integer.
     */
    public Integer getEnvDuration() {
        return Integer.parseInt(getEnvDuration);
    }
}
