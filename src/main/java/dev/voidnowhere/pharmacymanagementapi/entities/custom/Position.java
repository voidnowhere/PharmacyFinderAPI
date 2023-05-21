package dev.voidnowhere.pharmacymanagementapi.entities.custom;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Position {
    @NotNull
    private Float latitude;
    @NotNull
    private Float longitude;
}
