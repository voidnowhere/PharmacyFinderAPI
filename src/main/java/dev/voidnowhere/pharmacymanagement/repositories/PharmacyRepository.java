package dev.voidnowhere.pharmacymanagement.repositories;

import dev.voidnowhere.pharmacymanagement.entities.Pharmacy;
import dev.voidnowhere.pharmacymanagement.entities.custom.PharmacyPosition;
import dev.voidnowhere.pharmacymanagement.enums.WeekDayEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalTime;
import java.util.List;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
    List<Pharmacy> findAllByZoneId(Long zone_id);

    @Query("select p from Pharmacy p " +
            "inner join PharmacyWeekDay pwd on pwd.pharmacy.id = p.id " +
            "inner join WeekDay wd on wd.id = pwd.weekDay.id " +
            "where p.zone.id = :zone_id and " +
            "wd.weekDay = :weekDay and " +
            "(:time between pwd.firstShiftOpens and pwd.firstShiftCloses or " +
            ":time between pwd.secondShiftOpens and pwd.secondShiftCloses)"
    )
    List<Pharmacy> findAllAvailableByZoneId(Long zone_id, LocalTime time, WeekDayEnum weekDay);

    @Query("select new dev.voidnowhere.pharmacymanagement.entities.custom.PharmacyPosition(p.latitude, p.longitude) from Pharmacy p where p.id = :id")
    PharmacyPosition getPosition(Long id);
}
