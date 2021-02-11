package rs.ac.uns.ftn.isa.pharmacy.schedule.dtos;

import rs.ac.uns.ftn.isa.pharmacy.pharma.dtos.DrugPrescriptionDto;

import java.time.LocalDate;
import java.util.List;

public class ReportSubmissionDto {
    private Long appointmentId;
    private String appointmentInfo;
    private LocalDate issueDate;
    private List<DrugPrescriptionDto> prescribedDrugs;

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAppointmentInfo() {
        return appointmentInfo;
    }

    public void setAppointmentInfo(String appointmentInfo) {
        this.appointmentInfo = appointmentInfo;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public List<DrugPrescriptionDto> getPrescribedDrugs() {
        return prescribedDrugs;
    }

    public void setPrescribedDrugs(List<DrugPrescriptionDto> prescribedDrugs) {
        this.prescribedDrugs = prescribedDrugs;
    }
}
