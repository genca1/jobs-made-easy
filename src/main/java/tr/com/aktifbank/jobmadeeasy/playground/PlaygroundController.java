package tr.com.aktifbank.jobmadeeasy.playground;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        service.runHelloWorldJob();
    }
}
