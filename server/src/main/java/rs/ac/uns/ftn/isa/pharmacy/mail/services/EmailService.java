package rs.ac.uns.ftn.isa.pharmacy.mail.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Credentials;
import rs.ac.uns.ftn.isa.pharmacy.complaint.model.Response;
import rs.ac.uns.ftn.isa.pharmacy.mail.messages.*;
import rs.ac.uns.ftn.isa.pharmacy.mail.dtos.DrugNonExistentMailInfo;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.DrugReservation;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.StoredDrug;
import rs.ac.uns.ftn.isa.pharmacy.promotion.model.Promotion;
import rs.ac.uns.ftn.isa.pharmacy.promotion.model.Subscription;
import rs.ac.uns.ftn.isa.pharmacy.schedule.domain.Appointment;


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
    public void sendDrugDispensedMessage(DrugReservation drugReservation){
        var message = new DrugDispensedMessage(emailSender,drugReservation);
        message.send(
                SENDER_EMAIL,
                drugReservation.getPatient().getPerson().getCredentials().getEmail(),
                "Drug successfully retrieved.");
    }

    public void sendResponseNoticeMessage(Response response) {
        var message = new ComplaintResponseMessage(emailSender, response);
        message.send(
                SENDER_EMAIL,
                response
                        .getComplaint()
                        .getAuthor()
                        .getPerson()
                        .getCredentials()
                        .getEmail(),
                "Your complaint has been addressed"
        );
    }

    public void sendActivationMessage(Credentials credentials) {
        var message = new ActivationMessage(emailSender, credentials);
        message.send(SENDER_EMAIL, credentials.getEmail(), "Activation");
    }

    public void sendPromotionMessage(Promotion promotion, Subscription subscription) {
        var message = new PromotionMessage(emailSender, promotion);
        message.send(
                SENDER_EMAIL,
                subscription.getPerson().getCredentials().getEmail(),
                "New promotion by " + promotion.getPharmacy().getName()
        );
    }
}
