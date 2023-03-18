package dev.voidnowhere.pharmacymanagement.controllers.v1;

import dev.voidnowhere.pharmacymanagement.entities.City;
import dev.voidnowhere.pharmacymanagement.entities.Zone;
import dev.voidnowhere.pharmacymanagement.repositories.CityRepository;
import dev.voidnowhere.pharmacymanagement.repositories.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/cities")
public class CityController {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private ZoneRepository zoneRepository;

    @GetMapping
    public List<City> index() {
        return cityRepository.findAll();
    }

    @GetMapping("/{id}/zones")
    public List<Zone> zones(@PathVariable Long id) {
        return zoneRepository.findAllByCityId(id);
    }
}
