package rs.ac.uns.ftn.isa.pharmacy.pharma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.StoredDrug;

import java.util.List;

@Repository
public interface StoredDrugRepository extends JpaRepository<StoredDrug, Long> {
    @Query("select d from StoredDrug d where lower(d.drug.name) like lower(concat('%', :name, '%'))")
    List<StoredDrug> searchByName(@Param("name") String name);

    @Query("select d.quantity from StoredDrug d where d.pharmacy.id=:pharmacyId and d.drug.id=:drugId")
    Integer quantityInPharmacy(@Param("pharmacyId") Long pharmacyId, @Param("drugId") Long drugId);

    @Query("select d from StoredDrug d where d.pharmacy.id=:pharmacyId and d.drug.id=:drugId")
    StoredDrug getOneFromPharmacy(@Param("pharmacyId") Long pharmacyId, @Param("drugId") Long drugId);
}
