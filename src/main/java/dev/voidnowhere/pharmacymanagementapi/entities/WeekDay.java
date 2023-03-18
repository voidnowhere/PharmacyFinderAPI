package dev.voidnowhere.pharmacymanagementapi.entities;

import dev.voidnowhere.pharmacymanagementapi.enums.WeekDayEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class WeekDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private WeekDayEnum weekDay;
    @OneToMany(mappedBy = "weekDay")
    private List<PharmacyWeekDay> pharmacies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WeekDayEnum getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDayEnum weekDay) {
        this.weekDay = weekDay;
    }

    public List<PharmacyWeekDay> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(List<PharmacyWeekDay> pharmacies) {
        this.pharmacies = pharmacies;
    }
}
