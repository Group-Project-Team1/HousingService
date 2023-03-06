package com.beaconfire.housingservice.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "facility")
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name="house_id")
    @ToString.Exclude
    @JsonIgnore
    private House house;

    @Column(name = "type", length = 45)
    private String type;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "quantity")
    private String quantity;

    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private Set<FacilityReport> facilityReports;
}
