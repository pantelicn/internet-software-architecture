package rs.ac.uns.ftn.isa.pharmacy.pharma.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.DrugReservation;
import rs.ac.uns.ftn.isa.pharmacy.users.user.services.PatientService;

import java.time.LocalDate;
import java.util.List;

@Component
public class ScheduledReservationsCheck {
    private final DrugReservationService drugReservationService;
    private final PatientService patientService;

    public ScheduledReservationsCheck(DrugReservationService drugReservationService, PatientService patientService) {
        this.drugReservationService = drugReservationService;
        this.patientService = patientService;
    }

    @Scheduled(cron = "1 0 0 * * ?")
    public void checkDrugReservations() {
        penalize();
        System.out.println("Scheduled reservations check completed.");
    }

    private void penalize() {
        List<DrugReservation> expiredReservations = drugReservationService.findExpired(LocalDate.now());
        for (var reservation: expiredReservations) {
            if (!reservation.isDispensed()) {
                reservation.getPatient().penalize();
                patientService.update(reservation.getPatient(), reservation.getPatient().getId());
                drugReservationService.deleteById(reservation.getId());
                drugReservationService.updateStoredDrugQuantity(reservation.getStoredDrug().getId(), reservation.getQuantity());
            }
        }
        System.out.println(expiredReservations.size() + " penalties given.");
    }
}
