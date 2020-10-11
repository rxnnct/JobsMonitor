package ru.rxnnct.jobsmonitor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rxnnct.jobsmonitor.domain.JobsQty;

public interface JobsQtyRepo extends JpaRepository<JobsQty, Long> {
}
