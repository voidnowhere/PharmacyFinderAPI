package dev.voidnowhere.pharmacymanagementapi.controllers.v1;

import dev.voidnowhere.pharmacymanagementapi.entities.PharmacyWeekDay;
import dev.voidnowhere.pharmacymanagementapi.services.v1.PharmacyWeekDayService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/pharmacy_weekdays")
public class PharmacyWeekDayController {
    private final PharmacyWeekDayService pharmacyWeekDayService;

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWeekday(Principal principal, @Valid @RequestBody PharmacyWeekDay pharmacyWeekDay) {
        return pharmacyWeekDayService.update(principal, pharmacyWeekDay);
    }
}
