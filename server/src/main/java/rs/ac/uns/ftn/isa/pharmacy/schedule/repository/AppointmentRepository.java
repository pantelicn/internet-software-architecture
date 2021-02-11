package rs.ac.uns.ftn.isa.pharmacy.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.schedule.domain.Appointment;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("select a from Appointment a where a.type = 1 and a.patient is null")
    List<Appointment> findFreeExaminations();

    @Query("select a from Appointment a where a.patient.id = :patientId")
    List<Appointment> findAppointmentsByPatient(@Param("patientId") Long patientId);

    @Query("select a from Appointment a where a.patient.id= :patientId and a.type = 1")
    List<Appointment> findExaminationsByPatient(@Param("patientId") Long patientId);

    @Query("select a from Appointment a where a.patient.id= :patientId and a.type = 0")
    List<Appointment> findCounselingsByPatient(@Param("patientId") Long patientId);

    @Query("select a from Appointment a where a.shift.employee.id =:employeeId")
    List<Appointment> findAppointmentsByEmployee(@Param("employeeId") Long employeeId);

    @Query("select count(a.id) from Appointment a where a.shift.employee.id =:employeeId and a.patient.id =:patientId")
    int countByPatientAndEmployee(@Param("employeeId") long employeeId, @Param("patientId") long patientId);

    @Query("select count(a.id) from Appointment a where a.shift.pharmacy.id =:pharmacyId and a.patient.id =:patientId")
    int countByPatientAndPharmacy(@Param("pharmacyId") long pharmacyId, @Param("patientId") long patientId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Appointment> findById(Long id);
}
