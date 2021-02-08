package rs.ac.uns.ftn.isa.pharmacy.services.pharma;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.DrugReservation;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.DateException;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.repository.employee.EmployeeRepository;
import rs.ac.uns.ftn.isa.pharmacy.repository.pharma.DrugReservationRepository;
import rs.ac.uns.ftn.isa.pharmacy.services.notifiers.EmailService;

@Service
public class DrugReservationService {

    private final DrugReservationRepository reservationRepository;
    private final EmployeeRepository employeeRepository;
    private final EmailService emailService;

    public DrugReservationService(DrugReservationRepository reservationRepository, EmployeeRepository employeeRepository, EmailService emailService) {
        this.reservationRepository = reservationRepository;
        this.employeeRepository = employeeRepository;
        this.emailService = emailService;
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
        var reservedDrug = reservationRepository.findById(drugReservationId)
                .orElseThrow(() -> new EntityNotFoundException(DrugReservation.class.getSimpleName(),drugReservationId));
        emailService.sendDrugDispensedMessage(reservedDrug);
        reservationRepository.delete(reservedDrug);
    }
}
