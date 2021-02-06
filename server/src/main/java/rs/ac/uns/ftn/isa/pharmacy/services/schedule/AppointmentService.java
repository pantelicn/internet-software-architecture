package rs.ac.uns.ftn.isa.pharmacy.services.schedule;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.DrugPrescribed;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.Prescription;
import rs.ac.uns.ftn.isa.pharmacy.domain.schedule.Appointment;
import rs.ac.uns.ftn.isa.pharmacy.domain.schedule.AppointmentReport;
import rs.ac.uns.ftn.isa.pharmacy.dtos.ReportSubmissionDto;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.AppointmentTimeException;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.NoUpcomingAppointmentsException;
import rs.ac.uns.ftn.isa.pharmacy.repository.employee.EmployeeRepository;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.UserAccessException;
import rs.ac.uns.ftn.isa.pharmacy.repository.patients.PatientRepository;
import rs.ac.uns.ftn.isa.pharmacy.repository.pharma.DrugRepository;
import rs.ac.uns.ftn.isa.pharmacy.repository.pharma.StoredDrugRepository;
import rs.ac.uns.ftn.isa.pharmacy.repository.schedule.AppointmentRepository;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final EmployeeRepository employeeRepository;
    private final PatientRepository patientRepository;
    private final DrugRepository drugRepository;
    private final StoredDrugRepository storedDrugRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, EmployeeRepository employeeRepository, PatientRepository patientRepository, DrugRepository drugRepository, StoredDrugRepository storedDrugRepository) {
        this.appointmentRepository = appointmentRepository;
        this.employeeRepository = employeeRepository;
        this.patientRepository = patientRepository;
        this.drugRepository = drugRepository;
        this.storedDrugRepository = storedDrugRepository;
    }

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public Appointment findById(long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Appointment.class.getSimpleName(), id));
    }

    @Transactional
    public List<Appointment> getFreeExaminations(long pharmacyId) {
        return appointmentRepository.findFreeExaminations()
                .stream()
                .filter(a -> a.getShift().getPharmacy().getId() == pharmacyId)
                .collect(Collectors.toList());
    }

    public Appointment createFreeExamination(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getFreeExaminations(long pharmacyId, long dermatologistId) {
        return appointmentRepository.findFreeExaminations()
                .stream()
                .filter(a -> a.getShift().getPharmacy().getId() == pharmacyId &&
                        a.getShift().getEmployee().getId() == dermatologistId &&
                        a.getTerm().isInFuture())
                .collect(Collectors.toList());
    }

    public List<Appointment> getPatientAppointments(long patientId) {
        return appointmentRepository.findAppointmentsByPatient(patientId);
    }

    public void cancelPatientAppointment(long patientId, long appointmentId) {
        Appointment appointment = findById(appointmentId);
        if (appointment.getPatient().getId() != patientId) {
            throw new UserAccessException();
        } else if (!appointment.getTerm().isInFuture()) {
            throw new AppointmentTimeException();
        }
        appointment.setPatient(null);
        appointmentRepository.save(appointment);
    }
    public List<Appointment> getPastExaminations(long patientId){
        return appointmentRepository.findExaminationsByPatient(patientId)
                .stream()
                .filter(e -> e.getTerm().isInPast())
                .collect(Collectors.toList());
    }
    public List<Appointment> getPastCounselingsInPharmacy(long patientId,long pharmacistId){
        var pharmacyId = getPharmacyId(pharmacistId);
        return appointmentRepository
                .findCounselingsByPatient(patientId)
                .stream()
                .filter(a -> a.getShift().getPharmacy().getId() == pharmacyId)
                .collect(Collectors.toList());
    }
    public List<Appointment> getUpcomingAppointments(long employeeId) throws NoUpcomingAppointmentsException{
        var upcomingAppointments= appointmentRepository
                                                        .findAppointmentsByEmployee(employeeId)
                                                        .stream()
                                                        .filter(Appointment::isUpcoming)
                                                        .collect(Collectors.toList());
        validateUpcomingAppointments(upcomingAppointments);
        return upcomingAppointments;
    }

    public void freeUpExamination(long examinationId){
        var appointment = appointmentRepository.findById(examinationId)
                .orElseThrow(() -> new EntityNotFoundException(Appointment.class.getSimpleName(), examinationId));
        var patient = appointment.getPatient();

        patient.penalize();
        appointment.setPatient(null);

        appointmentRepository.save(appointment);
        patientRepository.save(patient);
    }

    public void freeUpCounseling(long counselingId) {
        var patient = appointmentRepository.findById(counselingId)
                .orElseThrow(() -> new EntityNotFoundException(Appointment.class.getSimpleName(), counselingId))
                .getPatient();

        patient.penalize();
        appointmentRepository.deleteById(counselingId);
        patientRepository.save(patient);
    }

    private void validateUpcomingAppointments(List<Appointment> upcomingAppointments) throws NoUpcomingAppointmentsException {
        if(upcomingAppointments.size() == 0)
            throw new NoUpcomingAppointmentsException();
    }

    private long getPharmacyId(long pharmacistId) {
        return employeeRepository.getOne(pharmacistId)
                .getShifts()
                .stream()
                .findFirst()
                .get()
                .getPharmacy()
                .getId();
    }

    public void submitAppointmentReport(ReportSubmissionDto reportSubmissionDto) {

        var appointment = appointmentRepository.getOne(reportSubmissionDto.getAppointmentId());
        var prescription = new Prescription();
        var appointmentReport =
                mapAppointmentReport(reportSubmissionDto, appointment, prescription);

        mapPrescription(reportSubmissionDto, prescription, appointmentReport);
        appointment.setAppointmentReport(appointmentReport);
        updateDrugQuantity(reportSubmissionDto,appointment);
        appointmentRepository.save(appointment);
    }

    private void updateDrugQuantity(ReportSubmissionDto reportSubmissionDto,Appointment appointment) {
        for( var prescribedDrug : reportSubmissionDto.getPrescribedDrugs()){
            var storedDrug = storedDrugRepository
                    .getOneFromPharmacy(appointment.getShift().getPharmacy().getId(), prescribedDrug.getDrugId());
            storedDrug.decrementQuantity();
            storedDrugRepository.save(storedDrug);

        }
    }

    private void mapPrescription(ReportSubmissionDto reportSubmissionDto, Prescription prescription, AppointmentReport appointmentReport) {
        prescription.setIssueDate(reportSubmissionDto.getIssueDate());
        prescription.setAppointmentReport(appointmentReport);
        prescription.setDrugs(mapPrescribedDrugs(reportSubmissionDto, prescription));
    }

    private AppointmentReport mapAppointmentReport(ReportSubmissionDto reportSubmissionDto, Appointment appointment, Prescription prescription) {
        var appointmentReport = new AppointmentReport();
        appointmentReport.setPrescription(prescription);
        appointmentReport.setAppointment(appointment);
        appointmentReport.setAppointmentInfo(reportSubmissionDto.getAppointmentInfo());
        return appointmentReport;
    }

    private List<DrugPrescribed> mapPrescribedDrugs(ReportSubmissionDto reportSubmissionDto, Prescription prescription) {
        var prescribedDrugs = new ArrayList<DrugPrescribed>();

        for(var drugPrescription : reportSubmissionDto.getPrescribedDrugs()){
            var prescribedDrug = new DrugPrescribed();
            prescribedDrug.setDrug(drugRepository.getOne(drugPrescription.getDrugId()));
            prescribedDrug.setPrescription(prescription);
            prescribedDrug.setTherapyDuration(drugPrescription.getDuration());
            prescribedDrugs.add(prescribedDrug);
        }

        return prescribedDrugs;
    }


}
