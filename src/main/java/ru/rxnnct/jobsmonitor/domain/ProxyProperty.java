package ru.rxnnct.jobsmonitor.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "proxy_properties")
@Data
public class ProxyProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "ip")
    private String ip;
    @Column(name = "port")
    private Long port;
    @Column(name = "active")
    private Boolean active;
}
