package tr.com.aktifbank.jobmadeeasy.playground;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.aktifbank.jobmadeeasy.jobs.HelloWorldJob;
import tr.com.aktifbank.jobmadeeasy.props.TimerProps;
import tr.com.aktifbank.jobmadeeasy.services.SchedulerService;

import java.util.List;

@Service
public class PlaygroundService {

    private final SchedulerService scheduler;

    @Autowired
    private PlaygroundService(final SchedulerService scheduler){
        this.scheduler = scheduler;
    }

    public void runHelloWorldJob() {
        TimerProps timerProps = TimerProps.builder()
                .callbackData("My callback data")
                .totalFireCount(25)
                .initialOffsetMs(1000)
                .printIntervalMs(2000)
                .build();
        timerProps.setRemainingFireCount(timerProps.getTotalFireCount());
        scheduler.Schedule(HelloWorldJob.class, timerProps);
    }

    public List<TimerProps> getAllJobs() throws SchedulerException {
        return scheduler.getAllRunningJobs();
    }

    public void deleteTimer(String timerId) throws SchedulerException{
        scheduler.deleteTimer(timerId);
    }

    public TimerProps getJobById(String jobId) throws SchedulerException {
        return scheduler.getJobById(jobId);
    }
}
