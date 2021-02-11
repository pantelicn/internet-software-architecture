package rs.ac.uns.ftn.isa.pharmacy.supply.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Order created by a pharmacy for a specific set of Drugs.
 */
@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDateTime offerDeadline;
    @OneToMany
    private List<OrderedDrug> orderedDrugs;
    private PurchaseOrder.Status status;

    public enum Status {
        AWAITING_OFFERS, FINISHED
    }

    public boolean isActive() {
        return status == Status.AWAITING_OFFERS;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getOfferDeadline() {
        return offerDeadline;
    }

    public void setOfferDeadline(LocalDateTime offerDeadline) {
        this.offerDeadline = offerDeadline;
    }

    public List<OrderedDrug> getOrderedDrugs() {
        return orderedDrugs;
    }

    public void setOrderedDrugs(List<OrderedDrug> orderedDrugs) {
        this.orderedDrugs = orderedDrugs;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
