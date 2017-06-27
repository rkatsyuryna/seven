package com.seven.models.domainobjects;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by ruslankatsyuryna on 6/24/17.
 */
@Entity
@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    private final String name;

    private final String data;

    @Column(name = "created_date")
    private final LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "report_company_id")
    private Company company;

}
