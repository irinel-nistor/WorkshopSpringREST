package ro.workshop.core.repository;

import org.springframework.data.repository.CrudRepository;
import ro.workshop.core.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
