package dev.voidnowhere.pharmacymanagementapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"week_day_id", "pharmacy_id"})})
public class PharmacyWeekDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Pharmacy pharmacy;
    @ManyToOne
    @JoinColumn(nullable = false)
    private WeekDay weekDay;
    @NotNull
    @Column(nullable = false)
    private LocalTime firstShiftOpens;
    @NotNull
    @Column(nullable = false)
    private LocalTime firstShiftCloses;
    @NotNull
    @Column(nullable = false)
    private LocalTime secondShiftOpens;
    @NotNull
    @Column(nullable = false)
    private LocalTime secondShiftCloses;

    public PharmacyWeekDay() {
    }

    public PharmacyWeekDay(Pharmacy pharmacy, WeekDay weekDay, LocalTime firstShiftOpens, LocalTime firstShiftCloses, LocalTime secondShiftOpens, LocalTime secondShiftCloses) {
        this.pharmacy = pharmacy;
        this.weekDay = weekDay;
        this.firstShiftOpens = firstShiftOpens;
        this.firstShiftCloses = firstShiftCloses;
        this.secondShiftOpens = secondShiftOpens;
        this.secondShiftCloses = secondShiftCloses;
    }

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
