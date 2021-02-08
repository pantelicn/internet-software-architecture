package rs.ac.uns.ftn.isa.pharmacy.controllers.pharma;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.isa.pharmacy.dtos.TimeOffRequestDto;
import rs.ac.uns.ftn.isa.pharmacy.services.pharma.TimeOffRequestService;

@RestController
@RequestMapping("/api/time-off")
public class TimeOffRequestController {
    private final TimeOffRequestService service;

    public TimeOffRequestController(TimeOffRequestService service) {
        this.service = service;
    }

    @PostMapping
    public void submit(@RequestBody TimeOffRequestDto timeOffRequestDto){
        service.submit(timeOffRequestDto);
    }
}
