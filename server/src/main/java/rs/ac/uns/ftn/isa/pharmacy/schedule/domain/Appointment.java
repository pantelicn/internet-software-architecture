package rs.ac.uns.ftn.isa.pharmacy.schedule.domain;

import rs.ac.uns.ftn.isa.pharmacy.finance.Money;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.Patient;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.domain.Shift;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.domain.Term;

import javax.persistence.*;

@Entity
@Table(name = "appointments")
@SequenceGenerator(name="appointment_seq", initialValue=100, allocationSize=1)
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "appointment_seq")
    private long id;
    @Embedded
    private Term term;
    private AppointmentType type;
    @Embedded
    private Money price;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="patient_id")
    private Patient patient;
    @ManyToOne(optional = false, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="shift_id")
    private Shift shift;
    @OneToOne(mappedBy = "appointment",cascade = CascadeType.ALL)
    private AppointmentReport appointmentReport;



    public Appointment(){}
    public Appointment(Term term, AppointmentType type,Patient patient, Shift shift) {
        this.term = term;
        this.type = type;
        this.price = new Money();
        this.patient = patient;
        this.shift = shift;
        this.appointmentReport = null;
    }

    public Boolean overlaps(Term term){
        return this.term.intersects(term) || this.term.equals(term);
    }
    public Boolean isReserved(){
        return this.getPatient() != null;
    }
    public Boolean isUpcoming(){
        return this.getTerm().isUpcoming() && this.isReserved() && this.getAppointmentReport()==null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public AppointmentType getType() {
        return type;
    }

    public void setType(AppointmentType type) {
        this.type = type;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public AppointmentReport getAppointmentReport() {
        return appointmentReport;
    }

    public void setAppointmentReport(AppointmentReport appointmentReport) {
        this.appointmentReport = appointmentReport;
    }
}
