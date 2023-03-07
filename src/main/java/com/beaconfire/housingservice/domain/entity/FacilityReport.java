package com.beaconfire.housingservice.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "facility_report")
public class FacilityReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="facility_id")
    @ToString.Exclude
    @JsonIgnore
    private Facility facility;

    @Column(name = "employee_id", nullable = false)
    private Integer employeeId;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "title", length = 45)
    private String title;

    @Column(name = "status", length = 45)
    private String status;

    @Column(name = "created_date")
    private Timestamp createDate;

    @OneToMany(mappedBy = "facilityReport", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<FacilityReportDetail> facilityReportDetails;


    public FacilityReport(Facility facility, Integer employeeId, String title, String description, String status, Timestamp createDate) {
        this.facility = facility;
        this.employeeId = employeeId;
        this.description = description;
        this.title = title;
        this.status = status;
        this.createDate = createDate;
    }
}
