package rs.ac.uns.ftn.isa.pharmacy.pharma.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.UserAccessException;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.DrugReservation;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.StoredDrug;
import rs.ac.uns.ftn.isa.pharmacy.pharma.exceptions.AllergyException;
import rs.ac.uns.ftn.isa.pharmacy.pharma.exceptions.DateException;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.pharma.repository.StoredDrugRepository;
import rs.ac.uns.ftn.isa.pharmacy.schedule.exceptions.PenaltiesException;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.repository.EmployeeRepository;
import rs.ac.uns.ftn.isa.pharmacy.pharma.repository.DrugReservationRepository;
import rs.ac.uns.ftn.isa.pharmacy.mail.services.EmailService;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.Patient;
import rs.ac.uns.ftn.isa.pharmacy.users.user.repository.PatientRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrugReservationService {

    private final DrugReservationRepository reservationRepository;
    private final EmployeeRepository employeeRepository;
    private final EmailService emailService;
    private final PatientRepository patientRepository;
    private final StoredDrugRepository storedDrugRepository;

    public DrugReservationService(DrugReservationRepository reservationRepository,
                                  EmployeeRepository employeeRepository,
                                  EmailService emailService,
                                  PatientRepository patientRepository,
                                  StoredDrugRepository storedDrugRepository) {
        this.reservationRepository = reservationRepository;
        this.employeeRepository = employeeRepository;
        this.emailService = emailService;
        this.patientRepository = patientRepository;
        this.storedDrugRepository = storedDrugRepository;
    }

    public DrugReservation getReserved(long drugReservationId, long pharmacistId){
        var reservedDrug = reservationRepository.findById(drugReservationId)
                .orElseThrow(() -> new EntityNotFoundException(DrugReservation.class.getSimpleName(),drugReservationId));
        var pharmacist = employeeRepository.getOne(pharmacistId);

        if(!pharmacist.worksIn(reservedDrug.getStoredDrug().getPharmacy()))
            throw new EntityNotFoundException(DrugReservation.class.getSimpleName(),drugReservationId);

        if(!reservedDrug.canBeDispensed())
            throw new DateException("Drug cannot be dispensed.");

        return reservedDrug;

    }
    public void dispense(long drugReservationId){
        var drugReservation = reservationRepository.findById(drugReservationId)
                .orElseThrow(() -> new EntityNotFoundException(DrugReservation.class.getSimpleName(),drugReservationId));
        emailService.sendDrugDispensedMessage(drugReservation);
        drugReservation.setDispensed(true);
        reservationRepository.save(drugReservation);
    }

    @Transactional
    public void updateStoredDrugQuantity(long storedDrugId, int quantity) {
        var storedDrug = storedDrugRepository.findById(storedDrugId)
                .orElseThrow(() -> new EntityNotFoundException(StoredDrug.class.getSimpleName(), storedDrugId));
        storedDrug.setQuantity(storedDrug.getQuantity() + quantity);
        storedDrugRepository.save(storedDrug);
    }

    @Transactional
    public void reserve(DrugReservation drugReservation, long patientId) {
        var patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException(Patient.class.getSimpleName(), patientId));
        if(patient.isBanned()) {
            throw new PenaltiesException("Patient has 3 or more penalties.");
        }
        var storedDrug = storedDrugRepository.findById(drugReservation.getStoredDrug().getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        StoredDrug.class.getSimpleName(),
                        drugReservation.getStoredDrug().getId())
                );
        if (drugReservation.isInPast()) {
            throw new DateException();
        }
        if (patient.getAllergicTo().stream().anyMatch(d -> d.getId() == storedDrug.getDrug().getId())) {
            throw new AllergyException();
        }
        drugReservation.setPatient(patient);
        drugReservation.setStoredDrug(storedDrug);
        updateStoredDrugQuantity(drugReservation.getStoredDrug().getId(), -drugReservation.getQuantity());
        try {
            DrugReservation reservation = reservationRepository.save(drugReservation);
            emailService.sendDrugReservedMessage(reservation);
        } catch (Exception e) {
            updateStoredDrugQuantity(drugReservation.getStoredDrug().getId(), drugReservation.getQuantity());
            throw e;
        }
    }

    public void cancelReservation(long drugReservationId, long patientId) {
        var drugReservation = reservationRepository.findById(drugReservationId)
                .orElseThrow(() -> new EntityNotFoundException(DrugReservation.class.getSimpleName(), drugReservationId));
        if (drugReservation.getPatient().getId() != patientId) {
            throw new UserAccessException();
        }
        if (drugReservation.isInPast(1)) {
            throw new DateException("Reservation cannot be canceled.");
        }
        updateStoredDrugQuantity(drugReservation.getStoredDrug().getId(), drugReservation.getQuantity());
        try {
            reservationRepository.deleteById(drugReservationId);
        } catch (Exception e) {
            updateStoredDrugQuantity(drugReservation.getStoredDrug().getId(), -drugReservation.getQuantity());
            throw e;
        }
    }

    public List<DrugReservation> findExpired(LocalDate now) {
        return reservationRepository.findExpired(now);
    }

    public List<DrugReservation> findPatientReservations(long patientId) {
        return reservationRepository.findAllByPatientId(patientId).stream()
                .filter(res -> !res.isDispensed())
                .collect(Collectors.toList());
    }

    public void deleteById(long id) {
        reservationRepository.deleteById(id);
    }

    public List<DrugReservation> findPatientReservationHistory(long patientId) {
        return reservationRepository.findAllByPatientId(patientId).stream()
                .filter(res -> res.isDispensed())
                .collect(Collectors.toList());
    }
}
