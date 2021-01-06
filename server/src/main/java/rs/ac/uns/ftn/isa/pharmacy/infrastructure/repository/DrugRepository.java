package rs.ac.uns.ftn.isa.pharmacy.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.Drug;

public interface DrugRepository extends JpaRepository<Drug, Long> {
}
