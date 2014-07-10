package ro.workshop.core.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ro.workshop.core.domain.*;
import ro.workshop.core.repository.OrderDetailsRepository;
import ro.workshop.core.repository.OrderRepository;

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
        order.setStatus(PrintStatus.WAITING);
        order.setDetails(new OrderDetails());
        order.getDetails().setMessage(String.valueOf(System.currentTimeMillis()));
        order.getDetails().setColor(Color.BLUE);
        order.getDetails().setSize(Size.EXTRA_LARGE);
        orderDetailsRepository.save(order.getDetails());
        orderRepository.save(order);
        logger.warn("Order created.");
    }
}
