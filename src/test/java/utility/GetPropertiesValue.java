package utility;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class GetPropertiesValue {

    public String getPropValues(String propFileName, String key) {

        String result = "";
        InputStream inputStream;

        try {
            Properties prop = new Properties();
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            result = prop.getProperty(key);
            return result != null ? new String(result.getBytes("ISO-8859-1"), "UTF-8") : "";
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return result;
    }
}
