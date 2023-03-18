package dev.voidnowhere.pharmacymanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private Float latitude;
    private Float longitude;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Zone zone;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private PharmacyOwner owner;
    @JsonIgnore
    @OneToMany(mappedBy = "pharmacy")
    private List<PharmacyWeekDay> weekDays;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public PharmacyOwner getOwner() {
        return owner;
    }

    public void setOwner(PharmacyOwner owner) {
        this.owner = owner;
    }

    public List<PharmacyWeekDay> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(List<PharmacyWeekDay> weekDays) {
        this.weekDays = weekDays;
    }
}
