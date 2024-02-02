package com.mnsfhxy.johnny_s_biological_notes.config;

public class Config {
    private ConfigReader defaultReader;
    private ConfigReader customReader;

    private Config() {
        defaultReader = new ConfigReader();
        customReader = new CustomConfigReader();
    }

    public String valueOf(String key) {
        String value = customReader.valueOf(key);
        return (value != null) ? value : defaultReader.valueOf(key);
    }

    public Float floatValueOf(String key) {
        return Float.valueOf(valueOf(key));
    }

    public Double doubleValueOf(String key) {
        return Double.valueOf(valueOf(key));
    }

    public Integer intValueOf(String key) {
        return Integer.valueOf(valueOf(key));
    }

    public Integer intValueOfHexString(String key) {
        String value = valueOf(key).replaceFirst("^0x", "").replaceFirst("^0X", "");
        return Integer.valueOf(value, 16);
    }

    public static Config getInstance() {
        return ConfigHolder.INSTANCE;
    }
    private static class ConfigHolder {
        private static final Config INSTANCE = new Config();
    }
}
