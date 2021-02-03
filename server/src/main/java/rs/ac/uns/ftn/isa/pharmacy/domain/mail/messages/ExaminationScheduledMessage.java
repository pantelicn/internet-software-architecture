package rs.ac.uns.ftn.isa.pharmacy.domain.mail.messages;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import rs.ac.uns.ftn.isa.pharmacy.domain.schedule.Appointment;

import java.text.MessageFormat;
import java.time.format.DateTimeFormatter;

public class ExaminationScheduledMessage extends EmailMessage<Appointment> {
    public ExaminationScheduledMessage(JavaMailSender emailSender, Appointment appointment) {
        super(emailSender, appointment);
    }

    @Override
    protected String formatMessage(Appointment appointment) {
        var formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        String message = "Dear {0} {1}, \n\nThe examination which will take place on {2}" +
                " held by a dermatologist {3} {4} is scheduled.\n\nExamination identifier is: {5} \n";
        return MessageFormat.format(message,
                appointment.getPatient().getPerson().getFirstName(),
                appointment.getPatient().getPerson().getLastName(),
                appointment.getTerm().getStart().format(formatter),
                appointment.getShift().getEmployee().getPerson().getFirstName(),
                appointment.getShift().getEmployee().getPerson().getLastName(),
                appointment.getId());

    }
}
