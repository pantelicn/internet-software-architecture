package rs.ac.uns.ftn.isa.pharmacy.schedule.dtos;

import rs.ac.uns.ftn.isa.pharmacy.users.employee.domain.Term;

import java.time.Duration;
import java.time.LocalDateTime;

public class CreatedAppointmentDto {
    private Long pharmacyId;
    private Long employeeId;
    private Long patientId;
    private LocalDateTime start;
    private Duration duration;


    public Term getTerm(){
        return new Term(start,duration);
    }
    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
