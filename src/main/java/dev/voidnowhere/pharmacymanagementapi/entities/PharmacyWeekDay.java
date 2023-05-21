package dev.voidnowhere.pharmacymanagementapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
