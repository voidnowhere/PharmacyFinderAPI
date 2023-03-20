package dev.voidnowhere.pharmacymanagementapi.repositories;

import dev.voidnowhere.pharmacymanagementapi.entities.Pharmacy;
import dev.voidnowhere.pharmacymanagementapi.entities.custom.PharmacyPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
    List<Pharmacy> findAllByZoneId(Long zone_id);

    @Query("select p from Pharmacy p " +
            "inner join PharmacyWeekDay pwd on pwd.pharmacy.id = p.id " +
            "inner join WeekDay wd on wd.id = pwd.weekDay.id " +
            "where p.zone.id = :zone_id and " +
            "wd.weekDay = UPPER(DAYNAME(now())) and " +
            "(CURRENT_TIME() between pwd.firstShiftOpens and pwd.firstShiftCloses or " +
            "CURRENT_TIME() between pwd.secondShiftOpens and pwd.secondShiftCloses) " +
            "order by ST_Distance_Sphere(point(p.longitude, p.latitude), point(:currentLongitude, :currentLatitude))"
    )
    List<Pharmacy> findAllAvailableByZoneId(Long zone_id, Double currentLatitude, Double currentLongitude);

    @Query("select new dev.voidnowhere.pharmacymanagementapi.entities.custom.PharmacyPosition(p.latitude, p.longitude) from Pharmacy p " +
            "where p.id = :id")
    PharmacyPosition getPosition(Long id);
}
