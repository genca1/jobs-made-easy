package tr.com.aktifbank.jobmadeeasy.util;

import org.quartz.*;
import tr.com.aktifbank.jobmadeeasy.model.TimerProperties;

import java.util.Date;

public final class TimerUtil {

    public TimerUtil() {}

    public static JobDetail jobDetail(final Class jobClass, TimerProperties props) {
        final JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(jobClass.getSimpleName(), props);

        return JobBuilder
                .newJob(jobClass)
                .withIdentity(jobClass.getSimpleName())
                .setJobData(jobDataMap)
                .build();
    }

    public static Trigger jobTrigger(final Class jobClass, TimerProperties props) {

        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(props.getPrintIntervalMs());

        if(props.isRunForever()){
            simpleScheduleBuilder = simpleScheduleBuilder.repeatForever();
        }
        else {
            simpleScheduleBuilder = simpleScheduleBuilder.withRepeatCount(props.getTotalFireCount() -1);
        }

        return TriggerBuilder
                .newTrigger()
                .withIdentity(jobClass.getSimpleName())
                .withSchedule(simpleScheduleBuilder)
                .startAt(new Date(System.currentTimeMillis() - props.getInitialOffsetMs()))
                .build();
    }
}
