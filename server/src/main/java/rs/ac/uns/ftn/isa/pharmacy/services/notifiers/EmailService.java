package rs.ac.uns.ftn.isa.pharmacy.services.notifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.domain.mail.messages.DrugReservedMessage;
import rs.ac.uns.ftn.isa.pharmacy.domain.mail.messages.ExaminationScheduledMessage;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.DrugReservation;
import rs.ac.uns.ftn.isa.pharmacy.domain.schedule.Appointment;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;
    private static final String SENDER_EMAIL = "isa.pharmacy.app@gmail.com";

    public void sendExaminationScheduledMessage(Appointment appointment){
        var examScheduledMessage = new ExaminationScheduledMessage(emailSender, appointment);
        examScheduledMessage.send(
                SENDER_EMAIL,
                appointment.getPatient().getPerson().getCredentials().getEmail(),
                "Examination scheduled."
        );
    }

    public void sendDrugReservedMessage(DrugReservation reservation) {
        var message = new DrugReservedMessage(emailSender, reservation);
        message.send(
                SENDER_EMAIL,
                reservation.getPatient().getPerson().getCredentials().getEmail(),
                "Medication reserved."
        );
    }

}
