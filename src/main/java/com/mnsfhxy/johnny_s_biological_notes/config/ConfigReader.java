package com.mnsfhxy.johnny_s_biological_notes.config;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Logger LOGGER = LogUtils.getLogger();
    protected static final String CONFIG_PATH = "data.properties";
    protected final Properties properties;

    protected ConfigReader() {
        properties = loadProperties();
    }

    protected String valueOf(String key) {
        return properties.get(key).toString();
    }

    protected InputStream inputStream() throws IOException {
        return ConfigReader.class
                        .getClassLoader()
                        .getResourceAsStream(CONFIG_PATH);
    }

    protected Properties loadProperties() {
        Properties properties = new Properties();

        try (InputStream inputStream =
                     this.inputStream()) {
            if(inputStream == null)
                return null;
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("读取配置文件异常：" + e.getMessage());
            throw new RuntimeException(e);
        }

        return properties;
    }
}
