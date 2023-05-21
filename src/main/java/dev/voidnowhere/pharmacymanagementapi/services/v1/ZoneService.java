package dev.voidnowhere.pharmacymanagementapi.services.v1;

import dev.voidnowhere.pharmacymanagementapi.entities.City;
import dev.voidnowhere.pharmacymanagementapi.entities.Zone;
import dev.voidnowhere.pharmacymanagementapi.repositories.CityRepository;
import dev.voidnowhere.pharmacymanagementapi.repositories.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ZoneService implements IDao<Zone, Long> {
    private final ZoneRepository zoneRepository;
    private final CityRepository cityRepository;

    public ResponseEntity<List<Zone>> findAllByCityId(Long id) {
        List<Zone> zones = zoneRepository.findAllByCityId(id);
        if (zones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(zones);
    }

    @Override
    public ResponseEntity<Zone> findById(Long id) {
        Optional<Zone> zone = zoneRepository.findById(id);
        return zone.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<?> save(Long cityId, Zone zone) {
        Optional<City> city = cityRepository.findById(cityId);
        if (city.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "city not found"));
        }
        if (zoneRepository.existsByCityIdAndName(cityId, zone.getName())) {
            return ResponseEntity.badRequest().body(Map.of("error", "name already exists"));
        }
        zone.setCity(city.get());
        zone = zoneRepository.save(zone);
        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(zone.getId())
                        .toUri()
                ).body(zone);
    }

    @Override
    public ResponseEntity<?> update(Zone zone) {
        Zone realZone = zoneRepository.findWithCityById(zone.getId());
        if (realZone == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "zone not found"));
        }
        if (zoneRepository.existsByCityIdAndNameAndIdNot(realZone.getCity().getId(), zone.getName(), zone.getId())) {
            return ResponseEntity.badRequest().body(Map.of("error", "name already exists"));
        }
        realZone.setName(zone.getName());
        return ResponseEntity.ok(zoneRepository.save(realZone));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        zoneRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
