package dev.voidnowhere.pharmacymanagementapi.repositories;

import dev.voidnowhere.pharmacymanagementapi.entities.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
    List<Zone> findAllByCityId(Long city_id);
}
