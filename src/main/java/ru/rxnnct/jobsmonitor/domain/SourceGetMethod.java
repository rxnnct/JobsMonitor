package ru.rxnnct.jobsmonitor.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "source_get_methods")
@Data
public class SourceGetMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "url")
    private String url;
    @Enumerated(EnumType.STRING)
    @Column(name = "source")
    private Source source;
}
