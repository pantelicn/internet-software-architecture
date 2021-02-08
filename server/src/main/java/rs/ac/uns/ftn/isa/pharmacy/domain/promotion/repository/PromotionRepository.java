package rs.ac.uns.ftn.isa.pharmacy.domain.promotion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.domain.promotion.model.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

}
