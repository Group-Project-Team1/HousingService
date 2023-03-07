package com.beaconfire.housingservice.domain.response;

import com.beaconfire.housingservice.domain.entity.FacilityReport;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Builder
public class FacilityReportsPageResponse {
    Integer page;
    List<FacilityReport> facilityReports;
}
