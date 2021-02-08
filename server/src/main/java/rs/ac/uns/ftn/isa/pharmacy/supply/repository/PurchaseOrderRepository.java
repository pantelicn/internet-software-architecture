package rs.ac.uns.ftn.isa.pharmacy.supply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.supply.domain.PurchaseOrder;

import java.util.List;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    @Query("select po from PurchaseOrder po where po.status = 'AWAITING_OFFERS'")
    List<PurchaseOrder> getAllActive();
}
