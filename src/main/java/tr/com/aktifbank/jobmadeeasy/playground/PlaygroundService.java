package tr.com.aktifbank.jobmadeeasy.playground;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.aktifbank.jobmadeeasy.jobs.HelloWorldJob;
import tr.com.aktifbank.jobmadeeasy.props.TimerProps;
import tr.com.aktifbank.jobmadeeasy.services.SchedulerService;

@Service
public class PlaygroundService {

    private final SchedulerService scheduler;

    @Autowired
    private PlaygroundService(final SchedulerService scheduler){
        this.scheduler = scheduler;
    }

    public void runHelloWorldJob(){
        TimerProps timerProps = TimerProps.builder()
                .callbackData("My callback data")
                .totalFireCount(5)
                .initialOffsetMs(1000)
                .printIntervalMs(2000)
                .build();
        scheduler.Schedule(HelloWorldJob.class, timerProps);
    }
}
