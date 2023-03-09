package com.beaconfire.housingservice.domain.response;

import com.beaconfire.housingservice.domain.entity.House;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AllHouseResponse {
    List<HouseAssignInfo> houseAssignInfo;
}
