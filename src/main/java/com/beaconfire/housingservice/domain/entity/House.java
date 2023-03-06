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
@Table(name = "house")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name="landlord_id")
    @ToString.Exclude
    @JsonIgnore
    private Landlord landlord;

    @Column(name = "max_occupant", unique = true, nullable = false)
    private int maxOccupant;

    @Column(name = "address", length = 45)
    private String address;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private Set<Facility> facilities;
}
