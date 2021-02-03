package rs.ac.uns.ftn.isa.pharmacy.repository.pharma;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.StoredDrug;

import java.util.List;

public interface StoredDrugRepository extends JpaRepository<StoredDrug, Long> {
    @Query("select d from StoredDrug d where lower(d.drug.name) like lower(concat('%', :name, '%'))")
    public List<StoredDrug> searchByName(@Param("name") String name);
}
