package ro.workshop.core.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ro.workshop.core.domain.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository repository;

    @Test
    public void testEmptyInsertIntoRepo(){
        Order order = new Order();
        repository.save(order);
        assertNotNull(repository.findOne(order.getId()));
    }

    @Test
    public void testInsertIntoRepo(){
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setMessage("bullcr@p");
        orderDetails.setSize(Size.LARGE);
        orderDetails.setColor(Color.BLUE);
        Order order = new Order();
        order.setDetails(orderDetails);
        order.setStatus(PrintStatus.WAITING);
        repository.save(order);

        Order retrieved = repository.findOne(order.getId());
        assertNotNull(retrieved);
        assertNotNull(retrieved.getDetails());
        assertNotNull(retrieved.getStatus());
        assertEquals(retrieved.getDetails().getMessage(), orderDetails.getMessage());
        assertEquals(retrieved.getStatus(), order.getStatus());
    }
}
