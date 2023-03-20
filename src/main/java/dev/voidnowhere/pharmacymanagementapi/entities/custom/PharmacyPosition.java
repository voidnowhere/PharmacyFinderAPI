package dev.voidnowhere.pharmacymanagementapi.entities.custom;

public class PharmacyPosition {
    private Double latitude;
    private Double longitude;

    public PharmacyPosition(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
