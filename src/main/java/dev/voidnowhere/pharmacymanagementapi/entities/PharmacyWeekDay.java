package dev.voidnowhere.pharmacymanagementapi.entities;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"week_day_id", "pharmacy_id"})})
public class PharmacyWeekDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Pharmacy pharmacy;
    @ManyToOne
    private WeekDay weekDay;
    private LocalTime firstShiftOpens;
    private LocalTime firstShiftCloses;
    private LocalTime secondShiftOpens;
    private LocalTime secondShiftCloses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    public LocalTime getFirstShiftOpens() {
        return firstShiftOpens;
    }

    public void setFirstShiftOpens(LocalTime firstShiftOpens) {
        this.firstShiftOpens = firstShiftOpens;
    }

    public LocalTime getFirstShiftCloses() {
        return firstShiftCloses;
    }

    public void setFirstShiftCloses(LocalTime firstShiftCloses) {
        this.firstShiftCloses = firstShiftCloses;
    }

    public LocalTime getSecondShiftOpens() {
        return secondShiftOpens;
    }

    public void setSecondShiftOpens(LocalTime secondShiftOpens) {
        this.secondShiftOpens = secondShiftOpens;
    }

    public LocalTime getSecondShiftCloses() {
        return secondShiftCloses;
    }

    public void setSecondShiftCloses(LocalTime secondShiftCloses) {
        this.secondShiftCloses = secondShiftCloses;
    }
}
