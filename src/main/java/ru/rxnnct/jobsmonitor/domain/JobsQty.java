package ru.rxnnct.jobsmonitor.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "jobs_qty")
@Data
public class JobsQty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String source;
    @Column(name = "qty")
    private Long qty;
    @Column(name = "record_date_time")
    private String recordDateTime;
}
