package ru.rxnnct.jobsmonitorwebapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rxnnct.jobsmonitorwebapp.domain.User;

public interface UserDetailsRepo extends JpaRepository<User, String> {
}
