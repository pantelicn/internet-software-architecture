package rs.ac.uns.ftn.isa.pharmacy.users.employee.domain;

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
        return (term.getStart().isAfter(this.getStart()) && term.getStart().isBefore(this.getEnd()))
                || (term.getEnd().isAfter(this.getStart()) && term.getEnd().isBefore(this.getEnd()))
                || (term.getStart().isBefore(this.getStart()) && term.getEnd().isAfter(this.getEnd()));
    }

    public boolean isBefore(Term term) {
        return term.getStart().isBefore(this.getStart()) && !intersects(term);
    }

    public boolean isAfter(Term term) {
        return term.getStart().isAfter(this.getStart()) && !intersects(term);
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
        return !this.getStart().isBefore(LocalDateTime.now().plus(time));
    }

    /**
     * Specific method used for fetching employees upcoming appontments
     * @return boolean value indicating whether term is in future or not.
     */
    public boolean isUpcoming(){
        return this.getStart().isAfter(LocalDateTime.now());
    }

    @Override
    public boolean equals(Object obj) {
        Term term = (Term)obj;
        return term.getStart().equals(this.getStart()) && term.duration.equals(this.duration);
    }

    public boolean isInRange(LocalDateTime start, LocalDateTime end) {
        if(end.isAfter(this.getStart()) && start.isBefore(this.getEnd()))
            return true;
        else if(start.isEqual(this.getStart()) && end.isAfter(this.getEnd()))
            return true;
        else return start.isBefore(this.getStart()) && end.isEqual(this.getEnd());
    }

    public boolean isInPast() {
        return this.getStart().isBefore(LocalDateTime.now());
    }

    public boolean contains(Term term) {
        return
            (this.start.isBefore(term.getStart()) || this.start.isEqual(term.getStart()))
            && (this.getEnd().isAfter(term.getEnd()) || this.getEnd().isEqual(term.getEnd()));
    }
}
