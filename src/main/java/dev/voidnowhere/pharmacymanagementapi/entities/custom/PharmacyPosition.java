package dev.voidnowhere.pharmacymanagementapi.entities.custom;

public class PharmacyPosition {
    private Float latitude;
    private Float longitude;

    public PharmacyPosition(Float latitude, Float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public Float getLongitude() {
        return longitude;
    }
}
