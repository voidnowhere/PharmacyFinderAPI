package dev.voidnowhere.pharmacymanagementapi.services.v1;

import dev.voidnowhere.pharmacymanagementapi.entities.Pharmacy;
import dev.voidnowhere.pharmacymanagementapi.entities.PharmacyWeekDay;
import dev.voidnowhere.pharmacymanagementapi.entities.WeekDay;
import dev.voidnowhere.pharmacymanagementapi.entities.Zone;
import dev.voidnowhere.pharmacymanagementapi.entities.custom.Position;
import dev.voidnowhere.pharmacymanagementapi.repositories.PharmacyRepository;
import dev.voidnowhere.pharmacymanagementapi.repositories.PharmacyWeekDayRepository;
import dev.voidnowhere.pharmacymanagementapi.repositories.WeekDayRepository;
import dev.voidnowhere.pharmacymanagementapi.repositories.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PharmacyService implements IDao<Pharmacy, Long> {
    @Autowired
    private ZoneRepository zoneRepository;
    @Autowired
    private PharmacyRepository pharmacyRepository;
    @Autowired
    private WeekDayRepository weekDayRepository;
    @Autowired
    private PharmacyWeekDayRepository pharmacyWeekDayRepository;

    public ResponseEntity<List<Pharmacy>> findAllAvailableByZoneId(Long id) {
        List<Pharmacy> pharmacies = pharmacyRepository.findAllAvailableByZoneId(id);
        if (pharmacies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pharmacies);
    }

    public ResponseEntity<List<Pharmacy>> findAllAvailableByZoneId(Long id, Double latitude, Double longitude) {
        List<Pharmacy> pharmacies = pharmacyRepository.findAllAvailableByZoneIdAndOrderByDistance(id, latitude, longitude);
        if (pharmacies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pharmacies);
    }

    @Override
    public ResponseEntity<?> save(Long zoneId, Pharmacy pharmacy) {
        Optional<Zone> zone = zoneRepository.findById(zoneId);
        if (zone.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "zone not found"));
        }
        pharmacy.setZone(zone.get());
        pharmacy = pharmacyRepository.save(pharmacy);
        LocalTime localTime = LocalTime.of(0, 0);
        List<PharmacyWeekDay> pharmacyWeekDays = new ArrayList<>();
        for (WeekDay weekDay : weekDayRepository.findAll()) {
            pharmacyWeekDays.add(new PharmacyWeekDay(pharmacy, weekDay, localTime, localTime, localTime, localTime));
        }
        pharmacyWeekDayRepository.saveAll(pharmacyWeekDays);
        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(pharmacy.getId())
                        .toUri()
                ).body(pharmacy);
    }

    @Override
    public ResponseEntity<?> update(Pharmacy pharmacy) {
        Optional<Pharmacy> optionalPharmacy = pharmacyRepository.findById(pharmacy.getId());
        if (optionalPharmacy.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "pharmacy not found"));
        }
        Pharmacy realPharmacy = optionalPharmacy.get();
        realPharmacy.setName(pharmacy.getName());
        realPharmacy.setAddress(pharmacy.getAddress());
        realPharmacy.setLatitude(pharmacy.getLatitude());
        realPharmacy.setLongitude(pharmacy.getLongitude());
        return ResponseEntity.ok(pharmacyRepository.save(realPharmacy));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        pharmacyRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Position> getPositionById(Long id) {
        Position pharmacyPosition = pharmacyRepository.getPositionById(id);
        if (pharmacyPosition == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pharmacyPosition);
    }
}
