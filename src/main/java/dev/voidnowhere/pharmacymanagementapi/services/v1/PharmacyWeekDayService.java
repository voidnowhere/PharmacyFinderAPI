package dev.voidnowhere.pharmacymanagementapi.services.v1;

import dev.voidnowhere.pharmacymanagementapi.entities.PharmacyWeekDay;
import dev.voidnowhere.pharmacymanagementapi.repositories.PharmacyWeekDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PharmacyWeekDayService implements IDao<PharmacyWeekDay, Long> {
    @Autowired
    private PharmacyWeekDayRepository pharmacyWeekDayRepository;

    public ResponseEntity<List<PharmacyWeekDay>> findAllByPharmacyId(Long id) {
        List<PharmacyWeekDay> pharmacyWeekDays = pharmacyWeekDayRepository.findAllByPharmacyId(id);
        if (pharmacyWeekDays.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pharmacyWeekDays);
    }

    @Override
    public ResponseEntity<?> update(PharmacyWeekDay pharmacyWeekDay) {
        Optional<PharmacyWeekDay> optionalPharmacyWeekDay = pharmacyWeekDayRepository.findById(pharmacyWeekDay.getId());
        if (optionalPharmacyWeekDay.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "pharmacy weekday not found"));
        }
        PharmacyWeekDay realPharmacyWeekDay = optionalPharmacyWeekDay.get();
        realPharmacyWeekDay.setFirstShiftOpens(pharmacyWeekDay.getFirstShiftOpens());
        realPharmacyWeekDay.setFirstShiftCloses(pharmacyWeekDay.getFirstShiftCloses());
        realPharmacyWeekDay.setSecondShiftOpens(pharmacyWeekDay.getSecondShiftOpens());
        realPharmacyWeekDay.setSecondShiftCloses(pharmacyWeekDay.getSecondShiftCloses());
        return ResponseEntity.ok(pharmacyWeekDayRepository.save(realPharmacyWeekDay));
    }
}
