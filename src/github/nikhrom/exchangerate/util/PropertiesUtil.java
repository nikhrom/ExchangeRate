package github.nikhrom.exchangerate.util;


import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.InputStream;
import java.util.Properties;

@UtilityClass
public class PropertiesUtil {
    private static final Properties PROPERTIES = new Properties();


    static {
        loadProperties();
    }

    @SneakyThrows
    private static void loadProperties() {
        try (InputStream resource = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(resource);
        }
    }

    public String get(String propertyName){
        return PROPERTIES.getProperty(propertyName);
    }
}
