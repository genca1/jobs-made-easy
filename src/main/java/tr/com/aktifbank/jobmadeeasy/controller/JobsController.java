package tr.com.aktifbank.jobmadeeasy.controller;

import lombok.RequiredArgsConstructor;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.*;
import tr.com.aktifbank.jobmadeeasy.model.TimerProperties;
import tr.com.aktifbank.jobmadeeasy.services.JobsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class JobsController {

    private final JobsService jobsService;

    @PostMapping("/hello")
    public void runCallTheRestJob(){
        jobsService.runCallTheRestJob();
    }

    @GetMapping("/")
    public List<TimerProperties> getAllJobs() throws SchedulerException {
        return jobsService.getAllJobs();
    }

    @GetMapping("/{jobid}")
    public TimerProperties getJobById(@PathVariable String jobid) throws SchedulerException {
        return jobsService.getJobById(jobid);
    }
}
