package rs.ac.uns.ftn.isa.pharmacy.domain.mail.messages;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import rs.ac.uns.ftn.isa.pharmacy.domain.schedule.Appointment;

import java.text.MessageFormat;
import java.time.format.DateTimeFormatter;

public class ExaminationScheduledMessage {
    private JavaMailSender emailSender;
    private SimpleMailMessage examScheduledMailMessage;
    private String message = "Dear {0} {1}, \n\nThe examination which will take place on {2}" +
            " held by a dermatologist {3} {4} is scheduled.\n\nExamination identifier is: {5} \n";

    public ExaminationScheduledMessage(JavaMailSender emailSender) {
        this.emailSender=emailSender;
        this.examScheduledMailMessage = new SimpleMailMessage();

    }

    @Async
    public void send(Appointment appointment){
        configureMessage(appointment);
        try {
            emailSender.send(examScheduledMailMessage);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void configureMessage(Appointment appointment) {
        var formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        message = MessageFormat.format(message,
                appointment.getPatient().getPerson().getFirstName(),
                appointment.getPatient().getPerson().getLastName(),
                appointment.getTerm().getStart().format(formatter),
                appointment.getShift().getEmployee().getPerson().getFirstName(),
                appointment.getShift().getEmployee().getPerson().getLastName(),
                appointment.getId());
        examScheduledMailMessage.setFrom("isa.pharmacy.app@gmail.com");
        examScheduledMailMessage.setTo(appointment.getPatient().getPerson().getCredentials().getEmail());
        examScheduledMailMessage.setSubject("Examination scheduled.");
        examScheduledMailMessage.setText(message);
    }
}
