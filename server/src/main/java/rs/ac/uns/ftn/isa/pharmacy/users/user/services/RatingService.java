package rs.ac.uns.ftn.isa.pharmacy.users.user.services;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Drug;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.DrugReservation;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.pharma.services.DrugReservationService;
import rs.ac.uns.ftn.isa.pharmacy.schedule.domain.Appointment;
import rs.ac.uns.ftn.isa.pharmacy.schedule.services.AppointmentService;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.domain.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RatingService {
    private final AppointmentService appointmentService;
    private final DrugReservationService drugReservationService;

    public RatingService(AppointmentService appointmentService, DrugReservationService drugReservationService) {
        this.appointmentService = appointmentService;
        this.drugReservationService = drugReservationService;
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


}
