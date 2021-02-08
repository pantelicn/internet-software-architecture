package rs.ac.uns.ftn.isa.pharmacy.mail.messages;

import org.springframework.mail.javamail.JavaMailSender;
import rs.ac.uns.ftn.isa.pharmacy.mail.dtos.DrugNonExistentMailInfo;

import java.text.MessageFormat;

public class DrugNonExistentMessage extends EmailMessage<DrugNonExistentMailInfo> {
    public DrugNonExistentMessage(JavaMailSender mailSender, DrugNonExistentMailInfo entity) {
        super(mailSender, entity);
    }

    @Override
    protected String formatMessage(DrugNonExistentMailInfo entity) {
        String message = "Dear {0} ,\n\nThere was a need for a medicine {1} recently, " +
                "but it didn't exist in our stock.\nConsider opening a procurement for the listed drug.";
        return MessageFormat.format(message, entity.getAdminFullName(),entity.getDrugName());
    }
}
