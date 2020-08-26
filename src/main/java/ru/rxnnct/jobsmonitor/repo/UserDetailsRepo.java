package ru.rxnnct.jobsmonitor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rxnnct.jobsmonitor.domain.User;

public interface UserDetailsRepo extends JpaRepository<User, String> {
}
