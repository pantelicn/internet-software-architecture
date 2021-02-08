package rs.ac.uns.ftn.isa.pharmacy.mail.messages;

import org.springframework.mail.javamail.JavaMailSender;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.DrugReservation;

import java.text.MessageFormat;
import java.time.format.DateTimeFormatter;

public class DrugReservedMessage extends EmailMessage<DrugReservation>{
    public DrugReservedMessage(JavaMailSender emailSender, DrugReservation reservation) {
        super(emailSender, reservation);
    }

    @Override
    protected String formatMessage(DrugReservation entity) {
        var formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String message = "Dear {0} {1}, \n\nYou have successfully reserved {2}" +
                ", quantity: {3}, from pharmacy: \"{4}\".\n Please pick up your reserved medication by specified date: {5}" +
                "\n\nReservation identifier is: {6} \n";
        return MessageFormat.format(message,
                entity.getPatient().getPerson().getFirstName(),
                entity.getPatient().getPerson().getLastName(),
                entity.getStoredDrug().getDrug().getName(),
                entity.getQuantity(),
                entity.getStoredDrug().getPharmacy().getName(),
                entity.getPickUpBefore().format(formatter),
                entity.getId()
        );
    }
}
