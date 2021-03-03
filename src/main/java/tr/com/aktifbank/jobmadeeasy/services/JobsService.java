package tr.com.aktifbank.jobmadeeasy.services;

import lombok.RequiredArgsConstructor;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;
import tr.com.aktifbank.jobmadeeasy.jobs.CallTheRestJob;
import tr.com.aktifbank.jobmadeeasy.model.TimerProperties;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobsService {

    private final SchedulerService schedulerService;

    public void runCallTheRestJob() {
        TimerProperties timerProperties = TimerProperties.builder()
                .callbackData("My callback data")
                .totalFireCount(25)
                .initialOffsetMs(1000)
                .printIntervalMs(2000)
                .build();
        timerProperties.setRemainingFireCount(timerProperties.getTotalFireCount());
        schedulerService.Schedule(CallTheRestJob.class, timerProperties);
    }

    public List<TimerProperties> getAllJobs() throws SchedulerException {
        return schedulerService.getAllRunningJobs();
    }

    public void deleteTimer(String timerId) throws SchedulerException{
        schedulerService.deleteTimer(timerId);
    }

    public TimerProperties getJobById(String jobId) throws SchedulerException {
        return schedulerService.getJobById(jobId);
    }
}
