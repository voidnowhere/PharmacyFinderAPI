package dev.voidnowhere.pharmacymanagementapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PharmacyDTO {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private Float latitude;
    private Float longitude;
    private Long zoneId;
    private Long cityId;
}
