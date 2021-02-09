package rs.ac.uns.ftn.isa.pharmacy.locale.services;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.locale.domain.City;
import rs.ac.uns.ftn.isa.pharmacy.locale.domain.Country;
import rs.ac.uns.ftn.isa.pharmacy.locale.repository.CityRepository;
import rs.ac.uns.ftn.isa.pharmacy.locale.repository.CountryRepository;

import java.util.List;

@Service
public class LocaleService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    public LocaleService(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    public List<City> getCitiesByCountry(long countryId) {
        return cityRepository.findByCountry(countryId);
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}
