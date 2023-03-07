package com.beaconfire.housingservice.domain.response;

import com.beaconfire.housingservice.domain.entity.House;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HouseResponse {
    House house;
}
