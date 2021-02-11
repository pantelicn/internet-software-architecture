package rs.ac.uns.ftn.isa.pharmacy.pharma.domain;

import rs.ac.uns.ftn.isa.pharmacy.schedule.domain.AppointmentReport;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "prescriptions")
@SequenceGenerator(name="prescription_seq", initialValue=100, allocationSize=1)
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prescription_seq")
    private long id;
    private LocalDate issueDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_report_id")
    private AppointmentReport appointmentReport;
    @OneToMany(mappedBy = "prescription",cascade = CascadeType.ALL)
    private List<DrugPrescribed> drugs;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public AppointmentReport getAppointmentReport() {
        return appointmentReport;
    }

    public void setAppointmentReport(AppointmentReport appointmentReport) {
        this.appointmentReport = appointmentReport;
    }

    public List<DrugPrescribed> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<DrugPrescribed> drugs) {
        this.drugs = drugs;
    }
}
