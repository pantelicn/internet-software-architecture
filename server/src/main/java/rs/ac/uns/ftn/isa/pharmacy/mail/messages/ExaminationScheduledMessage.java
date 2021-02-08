package rs.ac.uns.ftn.isa.pharmacy.mail.messages;

import org.springframework.mail.javamail.JavaMailSender;
import rs.ac.uns.ftn.isa.pharmacy.schedule.domain.Appointment;

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
