package ro.workshop.core.service;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.workshop.core.domain.Order;
import ro.workshop.core.repository.OrderDetailsRepository;
import ro.workshop.core.repository.OrderRepository;

import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public Order save(Order order) {
        orderDetailsRepository.save(order.getDetails());
        return orderRepository.save(order);
    }

    public List<Order> findAll() {
        return ImmutableList.copyOf(orderRepository.findAll());
    }

    public boolean exists(Long id){
        return orderRepository.exists(id);
    }

    public Order find(Long id) {
        return orderRepository.findOne(id);
    }

    public void delete(Long id) {
        orderRepository.delete(id);
    }
}
