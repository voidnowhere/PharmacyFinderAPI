package dev.voidnowhere.pharmacymanagementapi.controllers.v1;

import dev.voidnowhere.pharmacymanagementapi.entities.Pharmacy;
import dev.voidnowhere.pharmacymanagementapi.entities.PharmacyWeekDay;
import dev.voidnowhere.pharmacymanagementapi.entities.custom.Position;
import dev.voidnowhere.pharmacymanagementapi.services.v1.PharmacyService;
import dev.voidnowhere.pharmacymanagementapi.services.v1.PharmacyWeekDayService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/pharmacies")
public class PharmacyController {
    @Autowired
    private PharmacyService pharmacyService;
    @Autowired
    private PharmacyWeekDayService pharmacyWeekDayService;

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Pharmacy pharmacy) {
        return pharmacyService.update(pharmacy);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return pharmacyService.delete(id);
    }

    @GetMapping("/{id}/position")
    public ResponseEntity<Position> position(@PathVariable Long id) {
        return pharmacyService.getPositionById(id);
    }

    @GetMapping("/{id}/weekdays")
    public ResponseEntity<?> weekdays(@PathVariable Long id) {
        return pharmacyWeekDayService.findAllByPharmacyId(id);
    }

    @PutMapping("/{id}/weekdays")
    public ResponseEntity<?> updateWeekday(@Valid @RequestBody PharmacyWeekDay pharmacyWeekDay) {
        return pharmacyWeekDayService.update(pharmacyWeekDay);
    }
}
