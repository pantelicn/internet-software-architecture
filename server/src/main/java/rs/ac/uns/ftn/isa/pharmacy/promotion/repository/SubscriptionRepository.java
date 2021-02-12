package rs.ac.uns.ftn.isa.pharmacy.promotion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.promotion.model.Subscription;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query(value = "select count(s.id) from Subscription s where s.pharmacy.id = ?1 and s.person.id = ?2")
    int countByPharmacyAndPerson(long pharmacyId, long personId);

    @Query(value = "select s from Subscription s where s.pharmacy.id = ?1 and s.person.id = ?2")
    Subscription getByPharmacyAndPerson(long pharmacyId, long personId);

    @Query(value = "select s from Subscription s where s.pharmacy.id = ?1")
    List<Subscription> findByPharmacy(long pharmacyId);

    @Query(value = "select s from Subscription s where s.person.id = ?1")
    List<Subscription> getByPerson(long personId);
}
