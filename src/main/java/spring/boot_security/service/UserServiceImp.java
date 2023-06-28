package spring.boot_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.boot_security.dao.UserDao;
import spring.boot_security.models.Role;
import spring.boot_security.models.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service()
public class UserServiceImp implements UserDetailsService, UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {
      this.userDao = userDao;
    }

    @Override
    @Transactional
    public void add(User user) {
      userDao.save(user);
    }
    @Override
    @Transactional
    public void update(User user) {
        userDao.save(user);
    }
    @Override
    public List<User> listUsers() {
      return userDao.findAll();
    }
    @Override
    @Transactional
    public void delete(long id) {
      userDao.deleteById(id);
    }
    @Override
    public User getUserById(long id) {
        return userDao.findById(id).get();
    }
    @Override
    public User getUserByName(String email) {
      return userDao.findByEmail(email).get();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      User user = userDao.findByEmail(email).orElseThrow(() ->
              new UsernameNotFoundException("User doesn't exists"));
      return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
       return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toSet());
    }
}
