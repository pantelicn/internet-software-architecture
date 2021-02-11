package rs.ac.uns.ftn.isa.pharmacy.mail.messages;

import org.springframework.mail.javamail.JavaMailSender;
import rs.ac.uns.ftn.isa.pharmacy.promotion.model.Promotion;

public class PromotionMessage extends EmailMessage<Promotion> {


    public PromotionMessage(JavaMailSender mailSender, Promotion entity) {
        super(mailSender, entity);
    }

    @Override
    protected String formatMessage(Promotion promotion) {
        return promotion.getText();
    }
}
