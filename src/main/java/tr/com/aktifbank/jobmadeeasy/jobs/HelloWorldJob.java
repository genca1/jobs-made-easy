package tr.com.aktifbank.jobmadeeasy.jobs;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import tr.com.aktifbank.jobmadeeasy.props.TimerProps;


@Component
public class HelloWorldJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        JobDataMap map = jobExecutionContext.getJobDetail().getJobDataMap();
        TimerProps timerProps = (TimerProps) map.get(HelloWorldJob.class.getSimpleName());
        logger.info(timerProps.getCallbackData());
    }
}
