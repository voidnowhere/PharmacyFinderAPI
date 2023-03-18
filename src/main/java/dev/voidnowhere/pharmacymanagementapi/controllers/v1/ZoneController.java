package dev.voidnowhere.pharmacymanagementapi.controllers.v1;

import dev.voidnowhere.pharmacymanagementapi.entities.Pharmacy;
import dev.voidnowhere.pharmacymanagementapi.enums.WeekDayEnum;
import dev.voidnowhere.pharmacymanagementapi.repositories.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

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
    public List<Pharmacy> availablePharmacies(@PathVariable Long id) {
        return pharmacyRepository.findAllAvailableByZoneId(
                id,
                LocalTime.now(),
                WeekDayEnum.valueOf(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).toUpperCase())
        );
    }
}
