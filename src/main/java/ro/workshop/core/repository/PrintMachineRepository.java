package ro.workshop.core.repository;

import org.springframework.data.repository.CrudRepository;
import ro.workshop.core.domain.PrintMachine;

public interface PrintMachineRepository extends CrudRepository<PrintMachine, Long> {
}
