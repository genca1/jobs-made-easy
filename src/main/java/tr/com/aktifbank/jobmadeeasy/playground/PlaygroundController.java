package tr.com.aktifbank.jobmadeeasy.playground;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tr.com.aktifbank.jobmadeeasy.model.TimerProperties;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlaygroundController {

    private final PlaygroundService service;

    @Autowired
    public PlaygroundController(PlaygroundService service){
        this.service = service;
    }

    @PostMapping("/hello")
    public void runHelloWorldController(){
        service.runCallTheRestJob();
    }

    @GetMapping("/")
    public List<TimerProperties> getAllJobs() throws SchedulerException {
        return service.getAllJobs();
    }

    @GetMapping("/{jobid}")
    public TimerProperties getJobById(@PathVariable String jobid) throws SchedulerException {
        return service.getJobById(jobid);
    }


}
