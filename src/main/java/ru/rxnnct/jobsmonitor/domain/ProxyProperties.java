package ru.rxnnct.jobsmonitor.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "proxy-properties")
@Data
public class ProxyProperties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "ip")
    private String ip;
    @Column(name = "port")
    private Integer port;
    @Column(name = "active")
    private Boolean active;
}
