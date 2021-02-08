package rs.ac.uns.ftn.isa.pharmacy.mail.messages;

import org.springframework.mail.javamail.JavaMailSender;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Credentials;

public class ActivationMessage extends EmailMessage<Credentials> {

    public ActivationMessage(JavaMailSender mailSender, Credentials entity) {
        super(mailSender, entity);
    }

    private static final String rootUrl = "http://localhost:8080/api/auth/activate/";

    @Override
    protected String formatMessage(Credentials entity) {
        return
            "Hello, " + entity.getPerson().getFirstName() + " ,\n"
            + "\nPlease click the link below to activate your account:\n"
            + rootUrl + entity.getUid();
    }
}
