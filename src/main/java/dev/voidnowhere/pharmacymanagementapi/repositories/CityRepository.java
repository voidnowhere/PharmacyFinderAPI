package dev.voidnowhere.pharmacymanagementapi.repositories;

import dev.voidnowhere.pharmacymanagementapi.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
