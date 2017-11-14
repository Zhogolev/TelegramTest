package Sources;


import Sources.Routes.CommandDefault;
import org.telegram.telegrambots.api.objects.Message;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class Router {

    public Router(Message message) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Properties  prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream input = loader.getResourceAsStream("botconfig.properties");
        prop.load(input);

        if (message != null && message.hasText()) {

            String mText = message.getText();
            char commandChar = mText.charAt(0);
            if (commandChar == "/".charAt(0)){

                String command = message.getText().toLowerCase();
                String[] aCommandLine = command.split("/");

                if (aCommandLine.length > 1){

                    if  (prop.getProperty(aCommandLine[1]) != null) {

                        Class commandClass = Class.forName("Sources.Routes." + prop.getProperty(aCommandLine[1]));
                        Object commandObject = commandClass.newInstance();
                        commandClass.getMethod("execute",Message.class).invoke(commandObject,message);
                    }
            }else {
                    Class commandClass = Class.forName("Sources.Routes.CommandDefault");
                    Object commandObject = commandClass.newInstance();
                    commandClass.getMethod("execute",Message.class).invoke(commandObject,message);
                }

            }else {
                Class commandClass = Class.forName("Sources.Routes.CommandDefault");
                Object commandObject = commandClass.newInstance();
                commandClass.getMethod("execute",Message.class).invoke(commandObject,message);
            }
         }
    }
}

