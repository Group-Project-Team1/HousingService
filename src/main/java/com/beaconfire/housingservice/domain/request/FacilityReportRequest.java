package com.beaconfire.housingservice.domain.request;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FacilityReportRequest {
    private Integer facilityId;
    private Integer employeeId;
    private String description;
    private String title;
}
