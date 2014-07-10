package ro.workshop.core.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ro.workshop.core.domain.*;
import ro.workshop.core.repository.OrderDetailsRepository;
import ro.workshop.core.repository.OrderRepository;

import java.util.Random;

@Component
public class AddRandomOrdersTask {

    private static final Logger logger = LoggerFactory.getLogger(AddRandomOrdersTask.class);

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Scheduled(fixedRate = 30000)
    public void printOutDummyMessage() {
        Order order = new Order();
        order.setStatus(PrintStatus.getRandom());
        order.setDetails(new OrderDetails());
        order.getDetails().setMessage(getRandomName());
        order.getDetails().setColor(Color.getRandom());
        order.getDetails().setSize(Size.getRandom());
        orderDetailsRepository.save(order.getDetails());
        orderRepository.save(order);
        logger.warn("Order created.");
    }

    private String getRandomName(){
        int random = new Random().nextInt(10);
        switch (random){
            case 0:
                return "listen to ghostface";
            case 1:
                return "haters gonna hate";
            case 2:
                return "one tequila  ...";
            case 3:
                return "stop following me";
            case 4:
                return "vote for pedro";
            case 5:
                return "free tibet";
            case 6:
                return "sorry for partying";
            case 7:
                return "round is a shape";
            case 8:
                return "guns dont kill people";
            case 9:
                return "maybe not as agile";
            default:
                return "dont cross the river";
        }
    }
}
