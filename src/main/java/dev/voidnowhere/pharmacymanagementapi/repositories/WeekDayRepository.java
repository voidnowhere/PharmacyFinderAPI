package dev.voidnowhere.pharmacymanagementapi.repositories;

import dev.voidnowhere.pharmacymanagementapi.entities.WeekDay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeekDayRepository extends JpaRepository<WeekDay, Long> {
}
