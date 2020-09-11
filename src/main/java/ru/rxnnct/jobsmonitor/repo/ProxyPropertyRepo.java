package ru.rxnnct.jobsmonitor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rxnnct.jobsmonitor.domain.ProxyProperty;

public interface ProxyPropertyRepo extends JpaRepository<ProxyProperty, Long> {
}
