package rs.ac.uns.ftn.isa.pharmacy.services.notifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Credentials;
import rs.ac.uns.ftn.isa.pharmacy.domain.complaint.model.Response;
import rs.ac.uns.ftn.isa.pharmacy.domain.mail.messages.*;
import rs.ac.uns.ftn.isa.pharmacy.domain.mail.messages.dtos.DrugNonExistentMailInfo;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.DrugReservation;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.StoredDrug;
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
    public void sendInsufficientDrugQuantityMessage(StoredDrug storedDrug){
        var message = new InsufficientDrugQuantityMessage(emailSender,storedDrug);
        message.send(
                SENDER_EMAIL,
                storedDrug.getPharmacy().getPharmacyAdmin().getPerson().getCredentials().getEmail(),
                "Insufficient drug amount.");

    }
    public void sendDrugNonExistentMessage(DrugNonExistentMailInfo mailInfo){
        var message = new DrugNonExistentMessage(emailSender,mailInfo);
        message.send(
                SENDER_EMAIL,
                mailInfo.getAdminMail(),
                "Drug doesn't exist in our stock."
        );
    }

    public void sendResponseNoticeMessage(Response response) {
        var message = new ComplaintResponseMessage(emailSender, response);
        message.send(
                SENDER_EMAIL,
                response
                        .getComplaint()
                        .getAuthor()
                        .getCredentials()
                        .getEmail(),
                "Your complaint has been addressed"
        );
    }

    public void sendActivationMessage(Credentials credentials) {
        var message = new ActivationMessage(emailSender, credentials);
        message.send(SENDER_EMAIL, credentials.getEmail(), "Activation");
    }
}
