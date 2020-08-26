package ru.rxnnct.jobsmonitor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rxnnct.jobsmonitor.domain.SourceGetMethod;

public interface SourceGetMethodRepo extends JpaRepository<SourceGetMethod, Long> {
}
