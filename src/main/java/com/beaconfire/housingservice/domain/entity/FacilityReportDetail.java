package com.beaconfire.housingservice.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "facility_report_detail")
public class FacilityReportDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="facility_report_id")
    @ToString.Exclude
    @JsonIgnore
    private FacilityReport facilityReport;

    @Column(name = "employee_id", nullable = false)
    private Integer employeeId;

    @Column(name = "created_date")
    private Timestamp createDate;

    @Column(name = "last_modification_date")
    private Timestamp lastModificationDate;

    @Column(name = "comment", length = 45)
    private String comment;

    public FacilityReportDetail(FacilityReport facilityReport, Integer employeeId, Timestamp createDate, Timestamp lastModificationDate, String comment) {
        this.facilityReport = facilityReport;
        this.employeeId = employeeId;
        this.createDate = createDate;
        this.lastModificationDate = lastModificationDate;
        this.comment = comment;
    }
}
