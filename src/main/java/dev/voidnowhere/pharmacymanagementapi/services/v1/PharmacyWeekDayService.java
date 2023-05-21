package dev.voidnowhere.pharmacymanagementapi.services.v1;

import dev.voidnowhere.pharmacymanagementapi.entities.Pharmacy;
import dev.voidnowhere.pharmacymanagementapi.entities.PharmacyWeekDay;
import dev.voidnowhere.pharmacymanagementapi.repositories.PharmacyRepository;
import dev.voidnowhere.pharmacymanagementapi.repositories.PharmacyWeekDayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PharmacyWeekDayService implements IDao<PharmacyWeekDay, Long> {
    private final PharmacyWeekDayRepository pharmacyWeekDayRepository;
    private final PharmacyRepository pharmacyRepository;

    public ResponseEntity<List<PharmacyWeekDay>> findAllByPharmacyId(Principal principal, Long id) {
        Optional<Pharmacy> optionalPharmacy = pharmacyRepository.findById(id);
        if (optionalPharmacy.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (!principal.getName().equals(optionalPharmacy.get().getOwner().getId().toString())) {
            return ResponseEntity.badRequest().build();
        }
        List<PharmacyWeekDay> pharmacyWeekDays = pharmacyWeekDayRepository.findAllByPharmacyId(id);
        if (pharmacyWeekDays.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pharmacyWeekDays);
    }

    public ResponseEntity<?> update(Principal principal, PharmacyWeekDay pharmacyWeekDay) {
        Optional<PharmacyWeekDay> optionalPharmacyWeekDay = pharmacyWeekDayRepository.findById(pharmacyWeekDay.getId());
        if (optionalPharmacyWeekDay.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (!principal.getName().equals(optionalPharmacyWeekDay.get().getPharmacy().getOwner().getId().toString())) {
            return ResponseEntity.badRequest().build();
        }
        PharmacyWeekDay realPharmacyWeekDay = optionalPharmacyWeekDay.get();
        realPharmacyWeekDay.setFirstShiftOpens(pharmacyWeekDay.getFirstShiftOpens());
        realPharmacyWeekDay.setFirstShiftCloses(pharmacyWeekDay.getFirstShiftCloses());
        realPharmacyWeekDay.setSecondShiftOpens(pharmacyWeekDay.getSecondShiftOpens());
        realPharmacyWeekDay.setSecondShiftCloses(pharmacyWeekDay.getSecondShiftCloses());
        return ResponseEntity.ok(pharmacyWeekDayRepository.save(realPharmacyWeekDay));
    }
}
