package tr.com.aktifbank.jobmadeeasy.jobs;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import tr.com.aktifbank.jobmadeeasy.model.TimerProperties;


@Component
public class CallTheRestJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(CallTheRestJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        JobDataMap map = jobExecutionContext.getJobDetail().getJobDataMap();
        TimerProperties timerProperties = (TimerProperties) map.get(CallTheRestJob.class.getSimpleName());
        logger.info(timerProperties.getCallbackData());
    }
}
