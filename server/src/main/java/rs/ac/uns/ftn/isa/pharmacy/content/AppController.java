package rs.ac.uns.ftn.isa.pharmacy.content;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping
    public String app(Model model) {
        return "index";
    }
}
