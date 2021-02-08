package rs.ac.uns.ftn.isa.pharmacy.pharma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Drug;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {
}
