package tr.com.aktifbank.jobmadeeasy.services;


import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tr.com.aktifbank.jobmadeeasy.jobs.HelloWorldJob;
import tr.com.aktifbank.jobmadeeasy.props.TimerProps;

public class JMETriggerListener implements TriggerListener {

    private final SchedulerService schedulerService;
    private static final Logger logger = LoggerFactory.getLogger(SchedulerService.class);

    public JMETriggerListener(SchedulerService schedulerService){
        this.schedulerService = schedulerService;
    }


    @Override
    public String getName() {
        return JMETriggerListener.class.getName();
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {
        final String timerId = trigger.getKey().getName();
        final JobDataMap map = jobExecutionContext.getJobDetail().getJobDataMap();
        final TimerProps timerProps = (TimerProps) map.get(timerId);
        try {
            schedulerService.updateTimer(timerId, timerProps);
            if(!timerProps.isRunForever() && timerProps.getRemainingFireCount() != 0){
                int remainingFireCount = timerProps.getRemainingFireCount();
                timerProps.setRemainingFireCount(remainingFireCount - 1);
            }
        } catch (SchedulerException se){
            logger.error(se.getMessage());
        }
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {

    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {

    }
}
