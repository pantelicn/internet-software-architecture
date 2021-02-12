package rs.ac.uns.ftn.isa.pharmacy.schedule.dtos;

import rs.ac.uns.ftn.isa.pharmacy.finance.Money;
import rs.ac.uns.ftn.isa.pharmacy.schedule.domain.AppointmentType;

import java.time.Duration;
import java.time.LocalDateTime;

public class AppointmentDto {
    private long id;
    private LocalDateTime start;
    private Duration duration;
    private Money price;
    private String employeeName;
    private String employeeLastName;
    private AppointmentType type;
    private int employeeRating;
    private long employeeId;
    private long pharmacyId;

    public AppointmentDto(long id, LocalDateTime start, Duration duration, Money price, String employeeName, String employeeLastName, AppointmentType type, int rating, long employeeId, long pharmacyId) {
        this.id = id;
        this.start = start;
        this.duration = duration;
        this.price = price;
        this.employeeName = employeeName;
        this.employeeLastName = employeeLastName;
        this.type = type;
        this.employeeRating = rating;
        this.employeeId = employeeId;
        this.pharmacyId = pharmacyId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

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

    public Duration getDuration() {
        return duration;
    }

    public long getDurationMinutes() {
        return duration.toMinutes();
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public AppointmentType getType() {
        return type;
    }

    public void setType(AppointmentType type) {
        this.type = type;
    }

    public int getEmployeeRating() {
        return employeeRating;
    }

    public void setEmployeeRating(int employeeRating) {
        this.employeeRating = employeeRating;
    }
}
