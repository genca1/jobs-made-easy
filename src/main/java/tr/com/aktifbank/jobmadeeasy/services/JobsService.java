package tr.com.aktifbank.jobmadeeasy.services;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.aktifbank.jobmadeeasy.jobs.CallTheRestJob;
import tr.com.aktifbank.jobmadeeasy.model.TimerProperties;
import java.util.List;

@Service
public class JobsService {

    private final SchedulerService scheduler;

    @Autowired
    private JobsService(final SchedulerService scheduler){
        this.scheduler = scheduler;
    }

    public void runCallTheRestJob() {
        TimerProperties timerProperties = TimerProperties.builder()
                .callbackData("My callback data")
                .totalFireCount(25)
                .initialOffsetMs(1000)
                .printIntervalMs(2000)
                .build();
        timerProperties.setRemainingFireCount(timerProperties.getTotalFireCount());
        scheduler.Schedule(CallTheRestJob.class, timerProperties);
    }

    public List<TimerProperties> getAllJobs() throws SchedulerException {
        return scheduler.getAllRunningJobs();
    }

    public void deleteTimer(String timerId) throws SchedulerException{
        scheduler.deleteTimer(timerId);
    }

    public TimerProperties getJobById(String jobId) throws SchedulerException {
        return scheduler.getJobById(jobId);
    }
}
