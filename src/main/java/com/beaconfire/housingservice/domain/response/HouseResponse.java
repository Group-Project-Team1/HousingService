package com.beaconfire.housingservice.domain.response;

import com.beaconfire.housingservice.domain.entity.House;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HouseResponse {
    House house;
}
