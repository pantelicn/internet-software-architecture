package rs.ac.uns.ftn.isa.pharmacy.supply.domain;

import rs.ac.uns.ftn.isa.pharmacy.finance.Money;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.ExpiredException;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.LateDeadlineException;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidEntityException;

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

    /**
     * Validates all field values by checking for null-values and logically incorrect values (eg. expired PO).
     * List of checks: Deadline, price, purchase order, supplier
     */
    public void validateBeforeChange() throws LateDeadlineException, InvalidEntityException, ExpiredException {
        validateDeliveryDeadline();
        validatePrice();
        validatePurchaseOrder();
        validateSuppler();
    }

    public void validateDeliveryDeadline() throws LateDeadlineException {
        if (deliveryDeadline == null || deliveryDeadline.isBefore(LocalDateTime.now()))
            throw new LateDeadlineException();
    }
    public void validatePrice() throws InvalidEntityException {
        if (price == null || price.getAmount() <= 0) throw new InvalidEntityException("Price");
    }
    public void validatePurchaseOrder() throws InvalidEntityException, ExpiredException {
        if (purchaseOrder == null) throw new InvalidEntityException("Purchase order");
        if (!purchaseOrder.isActive()) throw new ExpiredException("Purchase order");
    }
    public void validateSuppler() throws InvalidEntityException {
        if (supplier == null) throw new InvalidEntityException("Supplier");
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
