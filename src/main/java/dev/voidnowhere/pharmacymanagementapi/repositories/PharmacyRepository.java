package dev.voidnowhere.pharmacymanagementapi.repositories;

import dev.voidnowhere.pharmacymanagementapi.dtos.PharmacyDTO;
import dev.voidnowhere.pharmacymanagementapi.entities.Pharmacy;
import dev.voidnowhere.pharmacymanagementapi.entities.custom.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
    @Query("""
            select p from Pharmacy p
            inner join PharmacyWeekDay pwd on pwd.pharmacy.id = p.id
            inner join WeekDay wd on wd.id = pwd.weekDay.id
            where p.zone.id = :id and
            wd.name = UPPER(DAYNAME(now())) and
            (CURRENT_TIME() between pwd.firstShiftOpens and pwd.firstShiftCloses or
            CURRENT_TIME() between pwd.secondShiftOpens and pwd.secondShiftCloses)
            """)
    List<Pharmacy> findAllAvailableByZoneId(Long id);

    @Query("""
            select p from Pharmacy p
            inner join PharmacyWeekDay pwd on pwd.pharmacy.id = p.id
            inner join WeekDay wd on wd.id = pwd.weekDay.id
            where p.zone.id = :id and wd.name = UPPER(DAYNAME(now())) and
            (CURRENT_TIME() between pwd.firstShiftOpens and pwd.firstShiftCloses or
            CURRENT_TIME() between pwd.secondShiftOpens and pwd.secondShiftCloses)
            order by ST_Distance_Sphere(point(p.longitude, p.latitude), point(:currentLongitude, :currentLatitude))
            """)
    List<Pharmacy> findAllAvailableByZoneIdAndOrderByDistance(Long id, Float currentLatitude, Float currentLongitude);

    @Query("""
            select new dev.voidnowhere.pharmacymanagementapi.entities.custom.Position(p.latitude, p.longitude) from Pharmacy p
            where p.id = :id
            """)
    Position getPositionById(Long id);

    @Query("""
            select new dev.voidnowhere.pharmacymanagementapi.dtos.PharmacyDTO(
                p.id, p.name, p.address, p.phoneNumber, p.latitude, p.longitude, p.zone.id, p.zone.city.id
            ) from Pharmacy p where p.owner.id = :ownerId
            """)
    PharmacyDTO findByOwnerId(UUID ownerId);
}
