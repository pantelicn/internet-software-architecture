package rs.ac.uns.ftn.isa.pharmacy.locale.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isa.pharmacy.locale.domain.City;
import rs.ac.uns.ftn.isa.pharmacy.locale.domain.Country;
import rs.ac.uns.ftn.isa.pharmacy.locale.services.LocaleService;

import java.util.List;

@Controller
@RequestMapping("api/locale")
public class LocaleController {

    private final LocaleService localeService;

    public LocaleController(LocaleService localeService) {
        this.localeService = localeService;
    }

    @GetMapping("cities/{countryId}")
    public ResponseEntity<List<City>> getCities(@PathVariable long countryId) {
        return ResponseEntity.ok(localeService.getCitiesByCountry(countryId));
    }

    @GetMapping("countries")
    public ResponseEntity<List<Country>> getCountries() {
        return ResponseEntity.ok(localeService.getAllCountries());
    }
}
