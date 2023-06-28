package spring.boot_security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot_security.models.User;
import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
