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
import java.util.Date;

//Класс в котором реализуется запись в базу

public  class CommandDefault extends Bot {

    public void execute(Message message){
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/beans.xml");
        ActivityService aService = (ActivityService) context.getBean("ActivityService");
        //Смотрим активн ли бот для чата
        if (!aService.getActivate(message.getChatId())) return;

        Long id =  message.getChatId();
        String data = message.getText();

        DBLogs logs = new DBLogs();
        logs.setId(id);
        logs.setContent(data);
        logs.setDate(new Date());
        logs.setUser(id);

        LogsService service = (LogsService) context.getBean("LogService");
        service.save(logs);

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Сообщение записано");
        System.out.print("data saved");

        try{
            Message msg = new Message();
            sendMessage(sendMessage);

        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}

