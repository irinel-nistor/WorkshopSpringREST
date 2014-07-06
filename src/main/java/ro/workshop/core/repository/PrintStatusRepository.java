package ro.workshop.core.repository;

import org.springframework.data.repository.CrudRepository;
import ro.workshop.core.domain.PrintStatus;

public interface PrintStatusRepository extends CrudRepository<PrintStatus, Long> {
}
