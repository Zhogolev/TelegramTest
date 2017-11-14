package Sources.Routes;


import Sources.Bot;
import db.Activity.ActivityService;
import db.Logs.LogsService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.telegram.telegrambots.api.objects.Message;

import java.math.BigInteger;

public  class CommandClean extends Bot{

    public void execute(Message message){
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/beans.xml");
        ActivityService aService = (ActivityService) context.getBean("ActivityService");
        //Смотрим активн ли бот для чата
        if (!aService.getActivate(message.getChatId())) return;

        LogsService service = (LogsService) context.getBean("LogService");
        service.delete(message.getChatId());

        System.out.print("Clean");
    }

}
