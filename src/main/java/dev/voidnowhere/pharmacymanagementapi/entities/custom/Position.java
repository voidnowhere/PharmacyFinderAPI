package dev.voidnowhere.pharmacymanagementapi.entities.custom;

import jakarta.validation.constraints.NotNull;

public class Position {
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;

    public Position(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
