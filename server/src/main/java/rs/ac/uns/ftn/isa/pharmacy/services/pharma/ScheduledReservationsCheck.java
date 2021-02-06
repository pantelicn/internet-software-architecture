package rs.ac.uns.ftn.isa.pharmacy.services.pharma;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.DrugReservation;
import rs.ac.uns.ftn.isa.pharmacy.repository.pharma.DrugReservationRepository;
import rs.ac.uns.ftn.isa.pharmacy.services.patients.PatientService;

import java.time.LocalDate;
import java.util.List;

@Component
public class ScheduledReservationsCheck {
    private final DrugReservationRepository drugReservationRepository;
    private final PatientService patientService;

    public ScheduledReservationsCheck(DrugReservationRepository drugReservationRepository, PatientService patientService) {
        this.drugReservationRepository = drugReservationRepository;
        this.patientService = patientService;
    }

    @Scheduled(cron = "1 0 0 * * ?")
    public void checkDrugReservations() {
        penalize();
        System.out.println("Scheduled reservations check completed.");
    }

    private void penalize() {
        List<DrugReservation> expiredReservations = drugReservationRepository.findExpired(LocalDate.now());
        for (var reservation: expiredReservations) {
            reservation.getPatient().penalize();
            patientService.update(reservation.getPatient(), reservation.getPatient().getId());
            drugReservationRepository.deleteById(reservation.getId());
        }
        System.out.println(expiredReservations.size() + " penalties given.");
    }
}
