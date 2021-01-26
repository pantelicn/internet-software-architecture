package rs.ac.uns.ftn.isa.pharmacy.domain.supply;

import rs.ac.uns.ftn.isa.pharmacy.domain.finance.Money;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Offer created by a Supplier for a specific PurchaseOrder.
 */
@Entity
@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Money price;
    private LocalDateTime deliveryDeadline;
    @OneToOne
    private PurchaseOrder order;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public LocalDateTime getDeliveryDeadline() {
        return deliveryDeadline;
    }

    public void setDeliveryDeadline(LocalDateTime deliveryDeadline) {
        this.deliveryDeadline = deliveryDeadline;
    }

    public PurchaseOrder getOrder() {
        return order;
    }

    public void setOrder(PurchaseOrder order) {
        this.order = order;
    }
}
