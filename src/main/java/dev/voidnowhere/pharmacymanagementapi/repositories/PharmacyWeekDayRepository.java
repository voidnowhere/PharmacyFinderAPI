package dev.voidnowhere.pharmacymanagementapi.repositories;

import dev.voidnowhere.pharmacymanagementapi.entities.PharmacyWeekDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PharmacyWeekDayRepository extends JpaRepository<PharmacyWeekDay, Long> {
    @Query("select pwd from PharmacyWeekDay pwd join fetch pwd.weekDay where pwd.pharmacy.id = :pharmacyId order by pwd.weekDay.id")
    List<PharmacyWeekDay> findAllByPharmacyId(Long pharmacyId);
}
