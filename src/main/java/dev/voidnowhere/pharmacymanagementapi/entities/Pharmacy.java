package dev.voidnowhere.pharmacymanagementapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 2, max = 100)
    @Column(nullable = false)
    private String name;
    @NotBlank
    @Size(min = 2, max = 100)
    @Column(nullable = false)
    private String address;
    @NotBlank
    @Column(nullable = false)
    private String phoneNumber;
    @NotNull
    @Column(nullable = false)
    private Float latitude;
    @NotNull
    @Column(nullable = false)
    private Float longitude;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Zone zone;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private User owner;
    @JsonIgnore
    @OneToMany(mappedBy = "pharmacy")
    private List<PharmacyWeekDay> weekDays;
}
