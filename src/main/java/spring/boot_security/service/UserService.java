package spring.boot_security.service;

import spring.boot_security.models.User;

import java.util.List;

public interface UserService {
    public void add(User user);
    public void update(User user);
    public List<User> listUsers();
    public void delete(long id);
    public User getUserById(long id);
    public User getUserByName(String email);
}
