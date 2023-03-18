package dev.voidnowhere.pharmacymanagement.repositories;

import dev.voidnowhere.pharmacymanagement.entities.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
    List<Zone> findAllByCityId(Long city_id);
}
