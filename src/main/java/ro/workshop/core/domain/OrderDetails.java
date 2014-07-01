package ro.workshop.core.domain;

import javax.persistence.*;

@Entity(name = "ORDER_DETAILS")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    @Enumerated(EnumType.STRING)
    private Color color;
    @Column
    @Enumerated(EnumType.STRING)
    private Size size;
    @Column
    private String message;
    @OneToOne(mappedBy = "details")
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
