package rs.ac.uns.ftn.isa.pharmacy.mail.messages;

import org.springframework.mail.javamail.JavaMailSender;
import rs.ac.uns.ftn.isa.pharmacy.complaint.model.Response;


public class ComplaintResponseMessage extends EmailMessage<Response> {

    public ComplaintResponseMessage(JavaMailSender mailSender, Response entity) {
        super(mailSender, entity);
    }

    @Override
    protected String formatMessage(Response response) {
        return
            "Your complaint has been addressed by the system administrator:\n"
            +
            response.getText()
            +
            "\nSincerely, SysAdmin.";
    }
}
