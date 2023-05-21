package dev.voidnowhere.pharmacymanagementapi.controllers.v1;

import dev.voidnowhere.pharmacymanagementapi.entities.City;
import dev.voidnowhere.pharmacymanagementapi.entities.Zone;
import dev.voidnowhere.pharmacymanagementapi.services.v1.CityService;
import dev.voidnowhere.pharmacymanagementapi.services.v1.ZoneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/cities")
public class CityController {
    private final CityService cityService;
    private final ZoneService zoneService;

    @GetMapping
    public ResponseEntity<List<City>> index() {
        return cityService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> show(@PathVariable Long id) {
        return cityService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> store(@Valid @RequestBody City city) {
        return cityService.save(city);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody City city) {
        return cityService.update(city);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return cityService.delete(id);
    }

    @GetMapping("/{id}/zones")
    public ResponseEntity<List<Zone>> zones(@PathVariable Long id) {
        return zoneService.findAllByCityId(id);
    }

    @PostMapping("/{id}/zones")
    public ResponseEntity<?> storeZone(@PathVariable Long id, @Valid @RequestBody Zone zone) {
        return zoneService.save(id, zone);
    }
}
