package rs.ac.uns.ftn.isa.pharmacy.users.user.services;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.schedule.domain.Appointment;
import rs.ac.uns.ftn.isa.pharmacy.schedule.services.AppointmentService;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.domain.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RatingService {
    private final AppointmentService appointmentService;

    public RatingService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
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
        return (List<Employee>) employeeMap.values();
    }
}
