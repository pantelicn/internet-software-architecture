package rs.ac.uns.ftn.isa.pharmacy.dtos;

import rs.ac.uns.ftn.isa.pharmacy.domain.finance.Money;

import java.time.Duration;
import java.time.LocalDateTime;

public class FreeAppointmentDto {
    private long id;
    private LocalDateTime start;
    private Duration duration;
    private Money price;
    private String dermatologistName;
    private String dermatologistLastName;

    public FreeAppointmentDto(long id, LocalDateTime start, Duration duration, Money price, String dermatologistName, String dermatologistLastName) {
        this.id = id;
        this.start = start;
        this.duration = duration;
        this.price = price;
        this.dermatologistName = dermatologistName;
        this.dermatologistLastName = dermatologistLastName;
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

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public String getDermatologistName() {
        return dermatologistName;
    }

    public void setDermatologistName(String dermatologistName) {
        this.dermatologistName = dermatologistName;
    }

    public String getDermatologistLastName() {
        return dermatologistLastName;
    }

    public void setDermatologistLastName(String dermatologistLastName) {
        this.dermatologistLastName = dermatologistLastName;
    }
}
