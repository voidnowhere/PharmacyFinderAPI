package dev.voidnowhere.pharmacymanagementapi.services;

import dev.voidnowhere.pharmacymanagementapi.entities.Zone;
import dev.voidnowhere.pharmacymanagementapi.repositories.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZoneService implements IDao<Zone> {
    @Autowired
    private ZoneRepository zoneRepository;

    @Override
    public List<Zone> findAll() {
        return zoneRepository.findAll();
    }

    @Override
    public Optional<Zone> findById(Long id) {
        return zoneRepository.findById(id);
    }

    @Override
    public Zone save(Zone zone) {
        return zoneRepository.save(zone);
    }

    @Override
    public Zone update(Zone zone) {
        return zoneRepository.save(zone);
    }

    @Override
    public void delete(Zone zone) {
        zoneRepository.delete(zone);
    }

    public List<Zone> findAllByCityId(Long id) {
        return zoneRepository.findAllByCityId(id);
    }
}
