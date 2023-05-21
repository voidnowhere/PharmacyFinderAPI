package dev.voidnowhere.pharmacymanagementapi.services.v1;

import dev.voidnowhere.pharmacymanagementapi.dtos.PharmacyDTO;
import dev.voidnowhere.pharmacymanagementapi.entities.Pharmacy;
import dev.voidnowhere.pharmacymanagementapi.entities.PharmacyWeekDay;
import dev.voidnowhere.pharmacymanagementapi.entities.WeekDay;
import dev.voidnowhere.pharmacymanagementapi.entities.Zone;
import dev.voidnowhere.pharmacymanagementapi.entities.custom.Position;
import dev.voidnowhere.pharmacymanagementapi.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.security.Principal;
import java.time.LocalTime;
import java.util.*;

@RequiredArgsConstructor
@Service
public class PharmacyService implements IDao<Pharmacy, Long> {
    private final UserRepository userRepository;
    private final ZoneRepository zoneRepository;
    private final PharmacyRepository pharmacyRepository;
    private final WeekDayRepository weekDayRepository;
    private final PharmacyWeekDayRepository pharmacyWeekDayRepository;

    public ResponseEntity<List<Pharmacy>> findAllAvailableByZoneId(Long id) {
        List<Pharmacy> pharmacies = pharmacyRepository.findAllAvailableByZoneId(id);
        if (pharmacies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pharmacies);
    }

    public ResponseEntity<List<Pharmacy>> findAllAvailableByZoneId(Long id, Float latitude, Float longitude) {
        List<Pharmacy> pharmacies = pharmacyRepository.findAllAvailableByZoneIdAndOrderByDistance(id, latitude, longitude);
        if (pharmacies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pharmacies);
    }

    public ResponseEntity<PharmacyDTO> myPharmacy(Principal principal) {
        PharmacyDTO pharmacy = pharmacyRepository.findByOwnerId(UUID.fromString(principal.getName()));
        return ResponseEntity.ok(pharmacy);
    }

    public ResponseEntity<?> save(Principal principal, Long zoneId, Pharmacy pharmacy) {
        Optional<Zone> zone = zoneRepository.findById(zoneId);
        if (zone.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "zone not found"));
        }
        pharmacy.setOwner(userRepository.findById(UUID.fromString(principal.getName())).get());
        pharmacy.setZone(zone.get());
        pharmacy = pharmacyRepository.save(pharmacy);
        LocalTime localTime = LocalTime.of(0, 0);
        List<PharmacyWeekDay> pharmacyWeekDays = new ArrayList<>();
        for (WeekDay weekDay : weekDayRepository.findAll()) {
            pharmacyWeekDays.add(
                    PharmacyWeekDay.builder()
                            .pharmacy(pharmacy)
                            .weekDay(weekDay)
                            .firstShiftOpens(localTime)
                            .firstShiftCloses(localTime)
                            .secondShiftOpens(localTime)
                            .secondShiftCloses(localTime)
                            .build()
            );
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

    public ResponseEntity<?> update(Principal principal, PharmacyDTO pharmacy) {
        Optional<Pharmacy> optionalPharmacy = pharmacyRepository.findById(pharmacy.getId());
        if (optionalPharmacy.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "pharmacy not found"));
        }
        if (!principal.getName().equals(optionalPharmacy.get().getOwner().getId().toString())) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Zone> optionalZone = zoneRepository.findById(pharmacy.getZoneId());
        if (optionalZone.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "zone not found"));
        }
        Pharmacy realPharmacy = optionalPharmacy.get();
        realPharmacy.setName(pharmacy.getName());
        realPharmacy.setAddress(pharmacy.getAddress());
        realPharmacy.setPhoneNumber(pharmacy.getPhoneNumber());
        realPharmacy.setLatitude(pharmacy.getLatitude());
        realPharmacy.setLongitude(pharmacy.getLongitude());
        realPharmacy.setZone(optionalZone.get());
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
