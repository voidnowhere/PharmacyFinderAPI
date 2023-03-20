package dev.voidnowhere.pharmacymanagementapi.controllers.v1;

import dev.voidnowhere.pharmacymanagementapi.entities.Pharmacy;
import dev.voidnowhere.pharmacymanagementapi.repositories.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/zones")
public class ZoneController {
    @Autowired
    private PharmacyRepository pharmacyRepository;

    @GetMapping("/{id}/pharmacies")
    public List<Pharmacy> pharmacies(@PathVariable Long id) {
        return pharmacyRepository.findAllByZoneId(id);
    }

    @GetMapping("/{id}/pharmacies/available")
    public List<Pharmacy> availablePharmacies(@PathVariable Long id, @RequestParam Double latitude, @RequestParam Double longitude) {
        return pharmacyRepository.findAllAvailableByZoneId(id, latitude, longitude);
    }
}
