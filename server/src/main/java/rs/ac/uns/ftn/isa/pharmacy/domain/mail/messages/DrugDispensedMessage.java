package rs.ac.uns.ftn.isa.pharmacy.domain.mail.messages;

import org.springframework.mail.javamail.JavaMailSender;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.DrugReservation;

import java.text.MessageFormat;
import java.time.format.DateTimeFormatter;

public class DrugDispensedMessage extends EmailMessage<DrugReservation>{
    public DrugDispensedMessage(JavaMailSender mailSender, DrugReservation entity) {
        super(mailSender, entity);
    }

    @Override
    protected String formatMessage(DrugReservation entity) {
        String message = "Dear {0} {1}, \n\nYou have successfully retrieved {2}" +
                ", quantity: {3}, from pharmacy: \"{4}\".";
        return MessageFormat.format(message,
                entity.getPatient().getPerson().getFirstName(),
                entity.getPatient().getPerson().getLastName(),
                entity.getStoredDrug().getDrug().getName(),
                entity.getQuantity(),
                entity.getStoredDrug().getPharmacy().getName()
        );
    }
}
