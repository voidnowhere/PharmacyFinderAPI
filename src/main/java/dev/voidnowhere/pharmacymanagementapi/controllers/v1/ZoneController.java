package dev.voidnowhere.pharmacymanagementapi.controllers.v1;

import dev.voidnowhere.pharmacymanagementapi.entities.Pharmacy;
import dev.voidnowhere.pharmacymanagementapi.services.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/zones")
public class ZoneController {
    @Autowired
    private PharmacyService pharmacyService;

    @GetMapping("/{id}/pharmacies")
    public List<Pharmacy> pharmacies(@PathVariable Long id) {
        return pharmacyService.findAllByZoneId(id);
    }

    @GetMapping("/{id}/pharmacies/available")
    public List<Pharmacy> availablePharmacies(@PathVariable Long id, @RequestParam Double latitude, @RequestParam Double longitude) {
        return pharmacyService.findAllAvailableByZoneId(id, latitude, longitude);
    }
}
