package tr.com.aktifbank.jobmadeeasy.services;

import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tr.com.aktifbank.jobmadeeasy.model.TimerProperties;
import tr.com.aktifbank.jobmadeeasy.util.TimerUtil;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchedulerService {

    private static final Logger logger = LoggerFactory.getLogger(SchedulerService.class);

    private final Scheduler scheduler;

    public void Schedule(Class jobClass, TimerProperties props){
        final JobDetail jobDetail = TimerUtil.jobDetail(jobClass, props);
        final Trigger jobTrigger = TimerUtil.jobTrigger(jobClass, props);

        try {
            scheduler.scheduleJob(jobDetail, jobTrigger);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void updateTimer(final String timerId, TimerProperties props) throws SchedulerException {
        final JobDetail jobDetail = scheduler.getJobDetail(new JobKey(timerId));
        jobDetail.getJobDataMap().put(timerId, props);
    }

    public void deleteTimer(String timerId) throws SchedulerException {
        scheduler.deleteJob(new JobKey(timerId));
    }

    public List<TimerProperties> getAllRunningJobs() throws SchedulerException {
        return scheduler.getJobKeys(GroupMatcher.anyGroup()).stream()
                .map(jobKey -> {
                    try {
                        JobDetail detail = scheduler.getJobDetail(jobKey);
                        return (TimerProperties) detail.getJobDataMap().get(jobKey.getName());
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public TimerProperties getJobById(String jobId) throws SchedulerException {
        final JobDetail jobDetail = scheduler.getJobDetail(new JobKey(jobId));
        return (TimerProperties) jobDetail.getJobDataMap().get(jobId);
    }

    @PostConstruct
    public void init() {
        try {
            scheduler.start();
            scheduler.getListenerManager().addTriggerListener(new JMETriggerListener(this));
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
