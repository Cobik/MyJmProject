package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
   static Properties properties = new Properties();
    private static PropertyReader instance;

    private PropertyReader() {

    }
    public static PropertyReader getInstance() {
        if (instance == null) {
            instance = new PropertyReader();
        }
        return instance;
    }

    public static Properties getProperties(String filename){
        try {
            properties.load(new FileReader(PropertyReader.class.getClassLoader().getResource(filename).getFile()));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
