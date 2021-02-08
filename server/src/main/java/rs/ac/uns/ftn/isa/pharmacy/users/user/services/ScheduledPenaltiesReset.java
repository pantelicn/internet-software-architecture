package rs.ac.uns.ftn.isa.pharmacy.users.user.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledPenaltiesReset {
    private final PatientService patientService;

    public ScheduledPenaltiesReset(PatientService patientService) {
        this.patientService = patientService;
    }

    @Scheduled(cron = "1 0 0 1 * ?")
    public void resetPenalties() {
        patientService.eraseAllPenalties();
        System.out.println("Patient penalties reset.");
    }
}
