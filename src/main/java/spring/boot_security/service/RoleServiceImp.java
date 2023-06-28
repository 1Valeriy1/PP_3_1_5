package spring.boot_security.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.boot_security.dao.RoleDao;
import spring.boot_security.models.Role;

import java.util.HashSet;
import java.util.Set;
@Service()
public class RoleServiceImp implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public void add(Role role) {
        roleDao.save(role);
    }

    @Override
    public Set<Role> getRoles() {
        return new HashSet(roleDao.findAll());
    }

}
