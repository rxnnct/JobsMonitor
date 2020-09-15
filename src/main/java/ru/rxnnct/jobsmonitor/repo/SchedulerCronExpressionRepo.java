package ru.rxnnct.jobsmonitor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rxnnct.jobsmonitor.domain.SchedulerCronExpression;

public interface SchedulerCronExpressionRepo extends JpaRepository<SchedulerCronExpression, Long> {
}
