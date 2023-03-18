package dev.voidnowhere.pharmacymanagement.controllers.v1;

import dev.voidnowhere.pharmacymanagement.entities.Pharmacy;
import dev.voidnowhere.pharmacymanagement.entities.custom.PharmacyPosition;
import dev.voidnowhere.pharmacymanagement.repositories.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("v1/pharmacies")
public class PharmacyController {
    @Autowired
    private PharmacyRepository pharmacyRepository;

    @GetMapping("/{id}")
    public Optional<Pharmacy> show(@PathVariable Long id) {
        return pharmacyRepository.findById(id);
    }

    @GetMapping("/{id}/position")
    public PharmacyPosition position(@PathVariable Long id) {
        return pharmacyRepository.getPosition(id);
    }
}
