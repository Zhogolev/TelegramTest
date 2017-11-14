package Sources;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Activity {

    public void Activate() throws IOException {
        Properties properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream input = loader.getResourceAsStream("botconfig.properties");
        properties.load(input);
        input.close();

        FileOutputStream out = new FileOutputStream("botconfig.properties");
        properties.setProperty("BotActive", "Y");
        properties.store(out, null);
        out.close();
    }

    public boolean getActivity() throws IOException {
        Properties properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream input = loader.getResourceAsStream("botconfig.properties");
        properties.load(input);
        input.close();

        return  properties.getProperty("BotActive") == "Y";
    }
}
