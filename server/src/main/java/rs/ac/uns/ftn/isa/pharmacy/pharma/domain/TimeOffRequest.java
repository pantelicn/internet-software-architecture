package rs.ac.uns.ftn.isa.pharmacy.pharma.domain;

import rs.ac.uns.ftn.isa.pharmacy.users.employee.domain.Employee;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "timeoff_requests")
@SequenceGenerator(name="time_off_seq", initialValue=100, allocationSize=1)
public class TimeOffRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "time_off_seq")
    private long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="pharmacy_id")
    private Pharmacy pharmacy;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="employee_id")
    private Employee employee;
    private String requestInfo;
    private LocalDate start;
    private LocalDate end;

    public TimeOffRequest() {
    }

    public TimeOffRequest(Pharmacy pharmacy, Employee employee, String requestInfo, LocalDate start, LocalDate end) {
        this.pharmacy = pharmacy;
        this.employee = employee;
        this.requestInfo = requestInfo;
        this.start = start;
        this.end = end;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(String requestInfo) {
        this.requestInfo = requestInfo;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
}
