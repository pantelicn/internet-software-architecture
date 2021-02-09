package rs.ac.uns.ftn.isa.pharmacy.users.user.services;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Drug;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.pharma.services.DrugReservationService;
import rs.ac.uns.ftn.isa.pharmacy.schedule.domain.Appointment;
import rs.ac.uns.ftn.isa.pharmacy.schedule.services.AppointmentService;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.domain.Employee;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.DrugRating;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.EmployeeRating;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.Patient;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.PharmacyRating;
import rs.ac.uns.ftn.isa.pharmacy.users.user.repository.DrugRatingRepository;
import rs.ac.uns.ftn.isa.pharmacy.users.user.repository.EmployeeRatingRepository;
import rs.ac.uns.ftn.isa.pharmacy.users.user.repository.PharmacyRatingRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RatingService {
    private final AppointmentService appointmentService;
    private final DrugReservationService drugReservationService;
    private final EmployeeRatingRepository employeeRatingRepository;
    private final DrugRatingRepository drugRatingRepository;
    private final PharmacyRatingRepository pharmacyRatingRepository;

    public RatingService(AppointmentService appointmentService,
                         DrugReservationService drugReservationService,
                         EmployeeRatingRepository employeeRatingRepository,
                         DrugRatingRepository drugRatingRepository,
                         PharmacyRatingRepository pharmacyRatingRepository) {
        this.appointmentService = appointmentService;
        this.drugReservationService = drugReservationService;
        this.employeeRatingRepository = employeeRatingRepository;
        this.drugRatingRepository = drugRatingRepository;
        this.pharmacyRatingRepository = pharmacyRatingRepository;
    }

    public List<Employee> getPatientPharmacistHistory(long patientId) {
        List<Appointment> appointments = appointmentService.getPatientCounselingHistory(patientId);
        return getEmployeesFromAppointments(appointments);
    }

    public List<Employee> getPatientDermatologistHistory(long patientId) {
        List<Appointment> appointments = appointmentService.getPatientExaminationHistory(patientId);
        return getEmployeesFromAppointments(appointments);
    }

    private List<Employee> getEmployeesFromAppointments(List<Appointment> appointments) {
        Map<Long, Employee> employeeMap = new HashMap<>();
        for (var appointment: appointments) {
            employeeMap.put(appointment.getShift().getEmployee().getId(),
                    appointment.getShift().getEmployee());
        }
        return new ArrayList(employeeMap.values());
    }

    public List<Drug> getPatientDrugHistory(long patientId) {
        Map<Long, Drug> drugMap = new HashMap<>();
        for (var drugReservation: drugReservationService.findPatientReservationHistory(patientId)) {
            drugMap.put(drugReservation.getStoredDrug().getDrug().getId(), drugReservation.getStoredDrug().getDrug());
        }
        return new ArrayList(drugMap.values());
    }

    public List<Pharmacy> getPatientPharmacyHistory(long patientId) {
        Map<Long, Pharmacy> pharmacyMap = new HashMap<>();
        for (var appointment: appointmentService.getPatientAppointmentHistory(patientId)) {
            pharmacyMap.put(appointment.getShift().getPharmacy().getId(), appointment.getShift().getPharmacy());
        }
        for (var drugReservations: drugReservationService.findPatientReservationHistory(patientId)) {
            pharmacyMap.put(drugReservations.getStoredDrug().getPharmacy().getId(),
                    drugReservations.getStoredDrug().getPharmacy());
        }
        return new ArrayList(pharmacyMap.values());
    }


    public void rateEmployee(EmployeeRating rating, long patientId) {
        rating.setPatient(new Patient());
        rating.getPatient().setId(patientId);
        var existingRating
                = employeeRatingRepository.findByEmployeeIdAndPatientId(rating.getEmployee().getId(), patientId);
        if (existingRating.isPresent()) {
            rating.setId(existingRating.get().getId());
        }
        employeeRatingRepository.save(rating);
    }

    public void rateDrug(DrugRating rating, long patientId) {
        rating.setPatient(new Patient());
        rating.getPatient().setId(patientId);
        var existingRating
                = drugRatingRepository.findByDrugIdAndPatientId(rating.getDrug().getId(), patientId);
        if (existingRating.isPresent()) {
            rating.setId(existingRating.get().getId());
        }
        drugRatingRepository.save(rating);
    }

    public void ratePharmacy(PharmacyRating rating, long patientId) {
        rating.setPatient(new Patient());
        rating.getPatient().setId(patientId);
        var existingRating
                = pharmacyRatingRepository.findByPharmacyIdAndPatientId(rating.getPharmacy().getId(), patientId);
        if (existingRating.isPresent()) {
            rating.setId(existingRating.get().getId());
        }
        pharmacyRatingRepository.save(rating);
    }
}
