package dev.voidnowhere.pharmacymanagementapi.services;

import dev.voidnowhere.pharmacymanagementapi.entities.Pharmacy;
import dev.voidnowhere.pharmacymanagementapi.entities.custom.PharmacyPosition;
import dev.voidnowhere.pharmacymanagementapi.repositories.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PharmacyService implements IDao<Pharmacy> {
    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Override
    public List<Pharmacy> findAll() {
        return pharmacyRepository.findAll();
    }

    @Override
    public Optional<Pharmacy> findById(Long id) {
        return pharmacyRepository.findById(id);
    }

    @Override
    public Pharmacy save(Pharmacy pharmacy) {
        return pharmacyRepository.save(pharmacy);
    }

    @Override
    public Pharmacy update(Pharmacy pharmacy) {
        return pharmacyRepository.save(pharmacy);
    }

    @Override
    public void delete(Pharmacy pharmacy) {
        pharmacyRepository.delete(pharmacy);
    }

    public PharmacyPosition getPositionById(Long id) {
        return pharmacyRepository.getPositionById(id);
    }

    public List<Pharmacy> findAllByZoneId(Long id) {
        return pharmacyRepository.findAllByZoneId(id);
    }

    public List<Pharmacy> findAllAvailableByZoneId(Long id, Double latitude, Double longitude) {
        return pharmacyRepository.findAllAvailableByZoneId(id, latitude, longitude);
    }
}
