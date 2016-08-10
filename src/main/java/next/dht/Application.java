package next.dht;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by next on 2016/8/10.
 */
@RestController
@SpringBootApplication
public class Application {

    @RequestMapping("/")
    @ResponseBody
    public String home()
    {
        return "Hello DHT";
    }

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(new Object[] { Application.class });
        springApplication.run(args);
    }

}
