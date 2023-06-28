package spring.boot_security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot_security.models.Role;

public interface RoleDao extends JpaRepository<Role, Long> {
}
