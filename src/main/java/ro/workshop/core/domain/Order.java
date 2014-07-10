package ro.workshop.core.domain;

import javax.persistence.*;

@Entity(name = "PRINT_ORDER")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "id_details")
    private OrderDetails details;
    @Column
    @Enumerated(EnumType.STRING)
    private PrintStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderDetails getDetails() {
        return details;
    }

    public void setDetails(OrderDetails details) {
        this.details = details;
    }

    public PrintStatus getStatus() {
        return status;
    }

    public void setStatus(PrintStatus printStatus) {
        this.status = printStatus;
    }
}
