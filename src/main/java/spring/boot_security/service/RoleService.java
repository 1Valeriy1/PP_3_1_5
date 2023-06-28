package spring.boot_security.service;

import spring.boot_security.models.Role;

import java.util.Set;

public interface RoleService {
    public void add(Role role);
    public Set<Role> getRoles();
}
