package rs.ac.uns.ftn.isa.pharmacy.domain.supply.model;

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
    private Offer.Status status;
    @OneToOne
    private PurchaseOrder purchaseOrder;
    @OneToOne
    private Supplier supplier;

    public enum Status {
        ACCEPTED, REJECTED, PENDING
    }

    public boolean isInPast() {
        return deliveryDeadline == null || deliveryDeadline.isBefore(LocalDateTime.now());
    }

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

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder order) {
        this.purchaseOrder = order;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
