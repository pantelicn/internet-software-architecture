package rs.ac.uns.ftn.isa.pharmacy.services.notifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.domain.mail.messages.ExaminationScheduledMessage;
import rs.ac.uns.ftn.isa.pharmacy.domain.schedule.Appointment;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendExaminationScheduledMessage(Appointment appointment){
        var examScheduledMessage = new ExaminationScheduledMessage(emailSender);
        examScheduledMessage.send(appointment);
    }

}
