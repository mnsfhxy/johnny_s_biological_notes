package com.mnsfhxy.johnny_s_biological_notes.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CustomConfigReader extends ConfigReader {
    public static final String USER_DIR_KEY = "user.dir";
    public static final String MODS_DIR = "mods";

    @Override
    protected InputStream inputStream() throws IOException {
        File file = new File(outsidePath());
        if(!file.exists())
            return null;
        return new FileInputStream(file);
    }

    private String outsidePath() {
        String basePath = System.getProperty(USER_DIR_KEY);
        return basePath +
                File.separator + MODS_DIR +
                File.separator + CONFIG_PATH;
    }

    @Override
    protected String valueOf(String key) {
        if(properties != null &&
        properties.containsKey(key)) {
            return super.valueOf(key);
        }

        return null;
    }
}
