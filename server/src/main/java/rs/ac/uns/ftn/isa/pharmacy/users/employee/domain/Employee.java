package rs.ac.uns.ftn.isa.pharmacy.users.employee.domain;

import rs.ac.uns.ftn.isa.pharmacy.users.person.domain.Person;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.TimeOffRequest;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.EmployeeRating;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.Rating;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="employees")
@SequenceGenerator(name = "employee_seq", initialValue = 100)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    private long id;
    @OneToOne
    private Person person;
    private EmployeeType employeeType;
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST},mappedBy = "employee")
    private List<Shift> shifts;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<TimeOffRequest> timeOffRequests;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<EmployeeRating> ratings;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
    }

    public List<TimeOffRequest> getTimeOffRequests() {
        return timeOffRequests;
    }

    public void setTimeOffRequests(List<TimeOffRequest> timeOffRequests) {
        this.timeOffRequests = timeOffRequests;
    }

    public boolean hasShiftAtPharmacy(Term term, double pharmacyId) {
        for(var shift:shifts)
            if (shift.getPharmacy().getId() == pharmacyId)
                if(term.isInRange(shift.getStart(),shift.getEnd()))
                    return true;
        return false;
    }

    public boolean isOccupied(Term term) {
        for(var shift : shifts){
            if(shift.hasScheduledExam(term))
                return true;
        }
        return false;
    }

    public Shift getAdequateShift(Term term) {
        for(var shift : shifts)
            if(term.isInRange(shift.getStart(),shift.getEnd()))
                return shift;
        return null;
    }

    public double getRating() {
        double totalRating = 0;
        int size = this.ratings.size();
        if (size == 0) return 0;
        for (var rating: this.ratings) {
            totalRating += rating.getRating();
        }
        return totalRating / size;
    }

    public boolean worksIn(Pharmacy pharmacy) {
        for(var shift:shifts)
            if (shift.getPharmacy().getId() == pharmacy.getId())
                    return true;
        return false;
    }

    public List<EmployeeRating> getRatings() {
        return ratings;
    }

    public void setRatings(List<EmployeeRating> ratings) {
        this.ratings = ratings;
    }
}
