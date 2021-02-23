package tr.com.aktifbank.jobmadeeasy.services;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class SchedulerService {

    private static final Logger logger = LoggerFactory.getLogger(SchedulerService.class);

    private final Scheduler scheduler;

    @Autowired
    public SchedulerService(Scheduler scheduler){
        this.scheduler = scheduler;
    }

    @PostConstruct
    public void init() {
        try {
            scheduler.start();
        }
        catch (SchedulerException e) {
            logger.info(e.getMessage());
        }
    }

    @PreDestroy
    public void shotDown() {
        try {
            scheduler.shutdown();
        }
        catch (SchedulerException e) {
            logger.info(e.getMessage());
        }
    }
}
