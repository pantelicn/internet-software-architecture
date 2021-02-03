package rs.ac.uns.ftn.isa.pharmacy.repository.pharma;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.DrugReservation;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DrugReservationRepository extends JpaRepository<DrugReservation, Long> {
    List<DrugReservation> findAllByPatientId(long patientId);

    @Query("select dr from DrugReservation dr where dr.pickUpBefore < :now")
    List<DrugReservation> findExpired(@Param("now") LocalDate now);
}
