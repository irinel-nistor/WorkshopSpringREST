package ro.workshop.core.repository;

import org.springframework.data.repository.CrudRepository;
import ro.workshop.core.domain.OrderDetails;

public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Long> {
}
