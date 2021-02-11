package rs.ac.uns.ftn.isa.pharmacy.supply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.supply.domain.OrderedDrug;

import java.util.List;

@Repository
public interface OrderedDrugRepository extends JpaRepository<OrderedDrug, Long> {

    @Query("select od from OrderedDrug od where od.order.id = :orderedDrugId")
    List<OrderedDrug> getByPurchaseOrderId(@Param("orderedDrugId") long purchaseOrderId);
}
