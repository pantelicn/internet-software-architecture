package rs.ac.uns.ftn.isa.pharmacy.pharma.domain;

import rs.ac.uns.ftn.isa.pharmacy.finance.Money;
import rs.ac.uns.ftn.isa.pharmacy.locale.domain.Address;
import rs.ac.uns.ftn.isa.pharmacy.users.admin.domain.Admin;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.domain.Shift;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pharmacies")
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pharmacy")
    private List<Shift> shifts;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "pharmacy")
    private List<TimeOffRequest> timeOffRequests;
    @OneToOne
    @JoinColumn(name="admin_id")
    private Admin pharmacyAdmin;
    private String description;
    private double rating;
    @Embedded
    private Money counselingPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
    }

    public Admin getPharmacyAdmin() {
        return pharmacyAdmin;
    }

    public void setPharmacyAdmin(Admin pharmacyAdmin) {
        this.pharmacyAdmin = pharmacyAdmin;
    }

    public List<TimeOffRequest> getTimeOffRequests() {
        return timeOffRequests;
    }

    public void setTimeOffRequests(List<TimeOffRequest> timeOffRequests) {
        this.timeOffRequests = timeOffRequests;
    }
    public Money getCounselingPrice() {
        return counselingPrice;
    }

    public void setCounselingPrice(Money appointmentPrice) {
        this.counselingPrice = appointmentPrice;
    }
}
