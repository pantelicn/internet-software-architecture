package rs.ac.uns.ftn.isa.pharmacy.users.employee.domain;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.schedule.domain.Appointment;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "shifts")
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDateTime start;
    private LocalDateTime end;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shift")
    private List<Appointment> appointments;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="pharmacy_id")
    private Pharmacy pharmacy;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="employee_id")
    private Employee employee;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public boolean hasScheduledExam(Term term) {
        for(var appointment : appointments)
            if(appointment.overlaps(term))
                return true;

        return false;
    }

    /**
     * Get shift as a {@link Term} object that represents the beginning, duration and the end of shift.
     * @return {@link Term} object.
     */
    public Term asTerm() {
        return new Term(start, Duration.between(start, end));
    }
}
