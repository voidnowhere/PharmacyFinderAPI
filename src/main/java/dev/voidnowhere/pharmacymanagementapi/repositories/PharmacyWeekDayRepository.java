package dev.voidnowhere.pharmacymanagementapi.repositories;

import dev.voidnowhere.pharmacymanagementapi.entities.PharmacyWeekDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PharmacyWeekDayRepository extends JpaRepository<PharmacyWeekDay, Long> {
    List<PharmacyWeekDay> findAllByPharmacyId(Long pharmacy_id);
}
