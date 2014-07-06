package ro.workshop.core.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class ProcessOrdersTask {

    private static final Logger logger = LoggerFactory.getLogger(ProcessOrdersTask.class);

    @Scheduled(fixedRate = 5000)
    public void printOutDummyMessage(){
        logger.warn("Current time: " + System.currentTimeMillis());
    }
}
