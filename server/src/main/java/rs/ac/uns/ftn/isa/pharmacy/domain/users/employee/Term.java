package rs.ac.uns.ftn.isa.pharmacy.domain.users.employee;

import javax.persistence.Embeddable;
import java.time.*;

@Embeddable
public class Term {
    private LocalDateTime start;
    private Duration duration;

    public Term() {
        this(LocalDateTime.now(), Duration.ZERO);
    }

    public Term(Duration duration) {
        this(LocalDateTime.now(), duration);
    }

    public Term(LocalDateTime start) {
        this(start, Duration.ZERO);
    }

    public Term(LocalDateTime start, Duration duration) {
        this.start = start;
        this.duration = duration;
    }

    public LocalDateTime getStart() {
        return start;
    }

    private void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return start.plus(duration);
    }

    public Duration getDuration() {
        return duration;
    }

    private void setDuration(Duration duration) {
        this.duration = duration;
    }

    public boolean intersects(Term term) {
        if ((term.getStart().isAfter(this.getStart()) && term.getStart().isBefore(this.getEnd()))
                || (term.getEnd().isAfter(this.getStart()) && term.getEnd().isBefore(this.getEnd()))
                || (term.getStart().isBefore(this.getStart()) && term.getEnd().isAfter(this.getEnd()))) {
            return true;
        }
        return false;
    }

    public boolean isBefore(Term term) {
        if (term.getStart().isBefore(this.getStart()) && !intersects(term)) {
            return true;
        }
        return false;
    }

    public boolean isAfter(Term term) {
        if (term.getStart().isAfter(this.getStart()) && !intersects(term)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the appointment is 24h in advance.
     * @return boolean value indicating whether condition is met.
     */
    public boolean isInFuture() {
        return isInFuture(Duration.ofHours(24));
    }

    /**
     * Checks if the appointment is given amount of time in advance.
     * @return boolean value indicating whether condition is met.
     */
    public boolean isInFuture(Duration time) {
        if (this.getStart().isBefore(LocalDateTime.now().plus(time))) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        Term term = (Term)obj;
        if (term.getStart().equals(this.getStart()) && term.duration.equals(this.duration)) {
            return true;
        }
        return false;
    }
}
