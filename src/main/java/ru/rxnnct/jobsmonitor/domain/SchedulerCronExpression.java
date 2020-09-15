package ru.rxnnct.jobsmonitor.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "scheduler_cron_expressions")
@Data
public class SchedulerCronExpression {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "expression")
    private String expression;
    @Enumerated(EnumType.STRING)
    @Column(name = "source")
    private Source source;
}
