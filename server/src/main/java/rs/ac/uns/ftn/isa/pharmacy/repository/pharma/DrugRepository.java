package rs.ac.uns.ftn.isa.pharmacy.repository.pharma;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.Drug;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {
}
