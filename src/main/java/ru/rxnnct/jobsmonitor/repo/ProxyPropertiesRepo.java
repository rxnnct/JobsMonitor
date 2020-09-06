package ru.rxnnct.jobsmonitor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rxnnct.jobsmonitor.domain.ProxyProperties;

public interface ProxyPropertiesRepo extends JpaRepository<ProxyProperties, Long> {
}
