package rs.ac.uns.ftn.isa.pharmacy.supply.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PurchaseOrderOverviewDto {
    private long id;
    private LocalDateTime offerDeadline;
    private List<OrderedDrugOverviewDto> orderedDrugs;

    public PurchaseOrderOverviewDto(long id, LocalDateTime offerDeadline, List<OrderedDrugOverviewDto> orderedDrugs) {
        this.id = id;
        this.offerDeadline = offerDeadline;
        this.orderedDrugs = orderedDrugs;
    }

    public static class OrderedDrugOverviewDto {
        private int amount;
        private String drugName;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getDrugName() {
            return drugName;
        }

        public void setDrugName(String drugName) {
            this.drugName = drugName;
        }

        public OrderedDrugOverviewDto(int amount, String drugName) {
            this.amount = amount;
            this.drugName = drugName;
        }


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

    public List<OrderedDrugOverviewDto> getOrderedDrugs() {
        return orderedDrugs;
    }

    public void setOrderedDrugs(List<OrderedDrugOverviewDto> orderedDrugs) {
        this.orderedDrugs = orderedDrugs;
    }
}
