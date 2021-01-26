package rs.ac.uns.ftn.isa.pharmacy.repository.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.domain.schedule.Appointment;

import java.util.stream.Stream;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("select a from Appointment a where a.type = 'Examination' and a.patient is null")
    public Stream<Appointment> findFreeExaminations();
}
