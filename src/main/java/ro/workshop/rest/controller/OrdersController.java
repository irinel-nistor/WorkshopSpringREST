package ro.workshop.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ro.workshop.core.service.OrderService;
import ro.workshop.rest.domain.Order;
import ro.workshop.util.Functions;

import java.util.List;

import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.Iterables.transform;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Order> getAllOrders() {
        return copyOf(transform(orderService.findAll(), Functions.toRest.order()));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable String id) {
        Long orderId = Long.valueOf(id);

        if (orderService.exists(orderId)){
            ro.workshop.core.domain.Order deleted = orderService.find(Long.valueOf(id));
            Order response = Functions.toRest.order().apply(deleted);
            orderService.delete(orderId);
            return new ResponseEntity<Order>(response, HttpStatus.OK);
        }else{

            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Order> viewOrder(@PathVariable String id){
        Long orderId = Long.valueOf(id);
        if (orderService.exists(orderId)){
            Order restOrder = Functions.toRest.order().apply(orderService.find(orderId));
            return new ResponseEntity<Order>(restOrder, HttpStatus.OK);
        }else{
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Order> createOrder(@RequestBody Order order, UriComponentsBuilder builder) {
        if (order.getId() == null){
            String newResourceId = saveOrder(order);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(
                    builder.path("/api/orders/{id}")
                            .buildAndExpand(newResourceId).toUri());
            return new ResponseEntity<Order>(order, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<Order>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        saveOrder(order);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    private String saveOrder(Order order){
        ro.workshop.core.domain.Order toSave = Functions.toCore.order().apply(order);
        ro.workshop.core.domain.Order saved = orderService.save(toSave);
        return saved.getId().toString();
    }
}
