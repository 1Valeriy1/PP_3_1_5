package spring.boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.boot_security.Exceptions.NoSuchUserException;
import spring.boot_security.Exceptions.UserIncorrectData;
import spring.boot_security.models.User;
import spring.boot_security.service.RoleService;
import spring.boot_security.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RestApiController {

    private final UserService userService;

    @Autowired
    public RestApiController(RoleService roleService, UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.listUsers(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<UserIncorrectData> createUser(@RequestBody User user, BindingResult bindingResult) {
        try {
            userService.add(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchUserException u) {
            throw new NoSuchUserException("User with username exist");
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserIncorrectData> pageDelete(@PathVariable("id") long id) {
        userService.delete(id);
        return new ResponseEntity<>(new UserIncorrectData(), HttpStatus.OK);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUser (@PathVariable("id") long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByUsername (Principal principal) {
        User user = userService.getUserByName(principal.getName());
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserIncorrectData> pageEdit(@PathVariable("id") long id,
                                                  @RequestBody User user) {
        try {
            String oldPassword = userService.getUserById(id).getPassword();
            if (oldPassword.equals(user.getPassword())) {
                System.out.println("TRUE");
                user.setPassword(oldPassword);
                userService.add(user);
            } else {
                System.out.println("FALSE");
                userService.add(user);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchUserException u) {
            throw new NoSuchUserException("User with username exist");
        }
    }
}