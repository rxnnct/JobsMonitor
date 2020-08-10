package ru.rxnnct.jobsmonitorwebapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rxnnct.jobsmonitorwebapp.domain.SourceGetMethod;

public interface SourceGetMethodRepo extends JpaRepository<SourceGetMethod, Long> {
}
