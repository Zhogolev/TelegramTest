package Sources.Routes;


import Sources.Activity;
import Sources.Bot;
import db.Activity.ActivityService;
import db.Logs.LogsService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;
import java.math.BigInteger;


public  class CommandStart extends Bot{
   public void execute(Message message) throws IOException {
       //Activity activity = new Activity();
       //activity.Activate();
       //Активация только еденожды - что было не правильно

       ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/beans.xml");
       ActivityService service = (ActivityService) context.getBean("ActivityService");
       boolean currentActiv = service.getActivate(message.getChatId());
       service.setActivate(message.getChatId(),true);

       SendMessage sendMessage = new SendMessage();
       sendMessage.enableMarkdown(true);
       sendMessage.setChatId(message.getChatId());
       sendMessage.setText("Добро пожаловать!");

       try{
           Message msg = new Message();
           sendMessage(sendMessage);

       }catch (TelegramApiException e){
           e.printStackTrace();
       }
   }
}
