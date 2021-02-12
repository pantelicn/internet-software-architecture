package rs.ac.uns.ftn.isa.pharmacy.supply.dto;

import rs.ac.uns.ftn.isa.pharmacy.supply.domain.OrderedDrug;

public class OrderedDrugMapper {
    public static PurchaseOrderOverviewDto.OrderedDrugOverviewDto objectToDto(OrderedDrug orderedDrug) {
        return new PurchaseOrderOverviewDto.OrderedDrugOverviewDto(
                orderedDrug.getAmount(), orderedDrug.getDrug().getName()
        );
    }
}
