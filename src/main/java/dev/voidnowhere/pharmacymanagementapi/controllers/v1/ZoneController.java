package dev.voidnowhere.pharmacymanagementapi.controllers.v1;

import dev.voidnowhere.pharmacymanagementapi.entities.Pharmacy;
import dev.voidnowhere.pharmacymanagementapi.entities.Zone;
import dev.voidnowhere.pharmacymanagementapi.entities.custom.Position;
import dev.voidnowhere.pharmacymanagementapi.services.v1.PharmacyService;
import dev.voidnowhere.pharmacymanagementapi.services.v1.ZoneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/zones")
public class ZoneController {
    private final ZoneService zoneService;
    private final PharmacyService pharmacyService;

    @GetMapping("/{id}")
    public ResponseEntity<Zone> show(@PathVariable Long id) {
        return zoneService.findById(id);
    }

    @PutMapping
    public ResponseEntity<?> updateZone(@Valid @RequestBody Zone zone) {
        return zoneService.update(zone);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return zoneService.delete(id);
    }

    @GetMapping("/{id}/pharmacies")
    public ResponseEntity<List<Pharmacy>> pharmacies(@PathVariable Long id) {
        return pharmacyService.findAllAvailableByZoneId(id);
    }

    @PostMapping("/{id}/pharmacies/closest")
    public ResponseEntity<List<Pharmacy>> availablePharmacies(
            @PathVariable Long id, @Valid @RequestBody Position position
    ) {
        return pharmacyService.findAllAvailableByZoneId(id, position.getLatitude(), position.getLongitude());
    }

    @PostMapping("/{id}/pharmacies")
    public ResponseEntity<?> storePharmacy(Principal principal, @PathVariable Long id, @Valid @RequestBody Pharmacy pharmacy) {
        return pharmacyService.save(principal, id, pharmacy);
    }
}
