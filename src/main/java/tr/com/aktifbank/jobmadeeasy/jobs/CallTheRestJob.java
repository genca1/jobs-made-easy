package tr.com.aktifbank.jobmadeeasy.jobs;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import tr.com.aktifbank.jobmadeeasy.model.TimerProperties;

@Component
@RequiredArgsConstructor
public class CallTheRestJob implements Job {

    private final String url;
    private static final Logger logger = LoggerFactory.getLogger(CallTheRestJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        JobDataMap map = jobExecutionContext.getJobDetail().getJobDataMap();
        TimerProperties timerProperties = (TimerProperties) map.get(CallTheRestJob.class.getSimpleName());
        logger.info(timerProperties.getCallbackData());
        TelegramBot bot = new TelegramBot("1602846906:AAEctjgJR4tjgxyLzIBOdwz7xc-BcNKqO_Y");
        long chatId = -1001392868457L;
        bot.execute(new SendMessage(chatId, "Selam " + (5 - timerProperties.getRemainingFireCount())));
    }
}
