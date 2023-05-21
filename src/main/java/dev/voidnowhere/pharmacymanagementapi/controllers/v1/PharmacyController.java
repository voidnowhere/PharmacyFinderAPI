package dev.voidnowhere.pharmacymanagementapi.controllers.v1;

import dev.voidnowhere.pharmacymanagementapi.dtos.PharmacyDTO;
import dev.voidnowhere.pharmacymanagementapi.entities.custom.Position;
import dev.voidnowhere.pharmacymanagementapi.services.v1.PharmacyService;
import dev.voidnowhere.pharmacymanagementapi.services.v1.PharmacyWeekDayService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/pharmacies")
public class PharmacyController {
    private final PharmacyService pharmacyService;
    private final PharmacyWeekDayService pharmacyWeekDayService;

    @GetMapping("/my")
    public ResponseEntity<PharmacyDTO> myPharmacy(Principal principal) {
        return pharmacyService.myPharmacy(principal);
    }

    @PutMapping
    public ResponseEntity<?> update(Principal principal, @Valid @RequestBody PharmacyDTO pharmacy) {
        return pharmacyService.update(principal, pharmacy);
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
    public ResponseEntity<?> weekdays(Principal principal, @PathVariable Long id) {
        return pharmacyWeekDayService.findAllByPharmacyId(principal, id);
    }
}
