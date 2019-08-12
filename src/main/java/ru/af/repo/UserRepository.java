package ru.af.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.af.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
