package Sources.Routes;

import Sources.Bot;
import db.Activity.ActivityService;
import db.Logs.LogsService;
import db.Logs.DBLogs;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.math.BigInteger;
import java.util.List;

public  class CommandMy extends Bot{
   public void execute(Message message){

       ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/beans.xml");
       ActivityService aService = (ActivityService) context.getBean("ActivityService");
       //Смотрим активн ли бот для чата
       if (!aService.getActivate(message.getChatId())) return;

       LogsService service = (LogsService) context.getBean("LogService");
       List<DBLogs> listlogs = service.getLogs(message.getChatId());
            String mString = new String();
            int index = 1;
            for (int i =  listlogs.size() - 1; i >= 0 ; i--) {
                mString += index;
                mString += ") " + listlogs.get(i).getContent();
                mString += '\n';
                index ++;
            }
       SendMessage sendMessage = new SendMessage();
       sendMessage.enableMarkdown(true);
       sendMessage.setChatId(message.getChatId());
       sendMessage.setText(mString);
       try{
           Message msg = new Message();
           sendMessage(sendMessage);

       }catch (TelegramApiException e){
            System.out.print(e.getMessage());
       }

    }

}

