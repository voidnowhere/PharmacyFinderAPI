package dev.voidnowhere.pharmacymanagementapi.services.v1;

import dev.voidnowhere.pharmacymanagementapi.entities.City;
import dev.voidnowhere.pharmacymanagementapi.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CityService implements IDao<City, Long> {
    @Autowired
    private CityRepository cityRepository;

    @Override
    public ResponseEntity<List<City>> findAll() {
        List<City> cities = cityRepository.findAll();
        if (cities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cities);
    }

    @Override
    public ResponseEntity<City> findById(Long id) {
        Optional<City> city = cityRepository.findById(id);
        return city.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<?> save(City city) {
        if (cityRepository.existsByName(city.getName())) {
            return ResponseEntity.badRequest().body(Map.of("error", "name already exists"));
        }
        city = cityRepository.save(city);
        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(city.getId())
                        .toUri()
                ).body(city);
    }

    @Override
    public ResponseEntity<?> update(City city) {
        Optional<City> optionalCity = cityRepository.findById(city.getId());
        if (optionalCity.isEmpty()){
            return ResponseEntity.badRequest().body(Map.of("error", "city not found"));
        }
        if (cityRepository.existsByNameAndIdNot(city.getName(), city.getId())) {
            return ResponseEntity.badRequest().body(Map.of("error", "name already exists"));
        }
        optionalCity.get().setName(city.getName());
        return ResponseEntity.ok(cityRepository.save(optionalCity.get()));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        cityRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
