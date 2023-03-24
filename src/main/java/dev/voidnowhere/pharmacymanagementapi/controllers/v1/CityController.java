package dev.voidnowhere.pharmacymanagementapi.controllers.v1;

import dev.voidnowhere.pharmacymanagementapi.entities.City;
import dev.voidnowhere.pharmacymanagementapi.entities.Zone;
import dev.voidnowhere.pharmacymanagementapi.services.CityService;
import dev.voidnowhere.pharmacymanagementapi.services.ZoneService;
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
    private CityService cityService;
    @Autowired
    private ZoneService zoneService;

    @GetMapping
    public List<City> index() {
        return cityService.findAll();
    }

    @GetMapping("/{id}/zones")
    public List<Zone> zones(@PathVariable Long id) {
        return zoneService.findAllByCityId(id);
    }
}
