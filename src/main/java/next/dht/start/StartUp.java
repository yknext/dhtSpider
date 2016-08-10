package next.dht.start;

import next.dht.service.MagnetService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Created by next on 2016/8/10.
 */
public class StartUp implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        MagnetService magnetService = event.getApplicationContext().getBean(MagnetService.class);
        magnetService.start();
    }
}
