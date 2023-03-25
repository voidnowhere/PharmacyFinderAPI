package dev.voidnowhere.pharmacymanagementapi.repositories;

import dev.voidnowhere.pharmacymanagementapi.entities.Zone;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
    List<Zone> findAllByCityId(Long city_id);

    @Query("select z from Zone z join fetch z.city where z.id = :id")
    Zone findWithCityById(Long id);

    boolean existsByCityIdAndName(Long city_id, @NotBlank String name);

    boolean existsByCityIdAndNameAndIdNot(Long city_id, @NotBlank String name, Long id);
}
