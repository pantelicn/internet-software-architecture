package rs.ac.uns.ftn.isa.pharmacy.pharma.services;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.pharma.repository.PharmacyRepository;

import java.util.List;

@Service
public class PharmacyService {
    private final PharmacyRepository repository;

    public PharmacyService(PharmacyRepository repository) {
        this.repository = repository;
    }

    public List<Pharmacy> findAll() {
        return repository.findAll();
    }
}
