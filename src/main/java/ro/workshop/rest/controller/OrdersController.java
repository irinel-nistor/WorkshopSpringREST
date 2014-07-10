package ro.workshop.rest.controller;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
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

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Order> getAllOrders() {
        return ImmutableList.copyOf(Iterables.transform(orderService.findAll(), Functions.toRest.order()));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable String id) {
        Long orderId = Long.valueOf(id);

        if (!orderService.exists(orderId)){
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }else{
            ro.workshop.core.domain.Order deleted = orderService.find(Long.valueOf(id));
            Order response = Functions.toRest.order().apply(deleted);
            orderService.delete(orderId);
            return new ResponseEntity<Order>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Order> createOrder(@RequestBody Order order, UriComponentsBuilder builder) {
        ro.workshop.core.domain.Order toSave = Functions.toCore.order().apply(order);
        ro.workshop.core.domain.Order saved = orderService.save(toSave);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/api/orders/{id}")
                        .buildAndExpand(saved.getId()).toUri());
        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }
}
