package rs.ac.uns.ftn.isa.pharmacy.schedule.dtos;

import java.time.Duration;
import java.time.LocalDateTime;

public class FreeAppointmentTermDto {
    private long appointmentId;
    private LocalDateTime start;
    private Duration duration;

    public FreeAppointmentTermDto(LocalDateTime start, Duration duration, long appointmentId) {
        this.start = start;
        this.duration = duration;
        this.appointmentId = appointmentId;
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
    public long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }
}
