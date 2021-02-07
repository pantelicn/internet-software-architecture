package rs.ac.uns.ftn.isa.pharmacy.services.pharma;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.DrugReservation;
import rs.ac.uns.ftn.isa.pharmacy.repository.pharma.DrugReservationRepository;
import rs.ac.uns.ftn.isa.pharmacy.services.PatientService;

import java.time.LocalDate;
import java.util.List;

@Component
public class ScheduledReservationsCheck {
    private final DrugService drugService;
    private final PatientService patientService;

    public ScheduledReservationsCheck(DrugService drugService, PatientService patientService) {
        this.drugService = drugService;
        this.patientService = patientService;
    }

    @Scheduled(cron = "1 0 0 * * ?")
    public void checkDrugReservations() {
        penalize();
        System.out.println("Scheduled reservations check completed.");
    }

    private void penalize() {
        List<DrugReservation> expiredReservations = drugService.findExpired(LocalDate.now());
        for (var reservation: expiredReservations) {
            reservation.getPatient().penalize();
            patientService.update(reservation.getPatient(), reservation.getPatient().getId());
            drugService.deleteById(reservation.getId());
            drugService.updateStoredDrugQuantity(reservation.getStoredDrug().getId(), reservation.getQuantity());
        }
        System.out.println(expiredReservations.size() + " penalties given.");
    }
}
