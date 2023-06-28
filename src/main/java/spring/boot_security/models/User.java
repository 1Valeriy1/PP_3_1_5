package spring.boot_security.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "name")
   private String firstName;

   @Column(name = "password")
   private String password;

   @Column(name = "last_name")
   private String lastName;

   @Column(name = "email")
   private String email;

   @Column(name = "age")
   private int age;

   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(name = "users_roles",
           joinColumns = @JoinColumn(name = "user_id"),
           inverseJoinColumns = @JoinColumn(name = "role_id"))
   private Set<Role> roles;

   public User() {
   }

   public User(String firstName, String password, String lastName, String email, int age, Set<Role> roles) {
      this.firstName = firstName;
      this.password = password;
      this.lastName = lastName;
      this.email = email;
      this.age = age;
      this.roles = roles;
   }

   public void addRole(Role role) {
      roles.add(role);
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public int getAge() {
      return age;
   }

   public void setAge(int age) {
      this.age = age;
   }

   public Set<Role> getRoles() {
      return roles;
   }

   public void setRoles(Set<Role> roles) {
      this.roles = roles;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return getRoles();
   }

   @Override
   @JsonIgnore
   public String getUsername() {
      return email;
   }

   @Override
   @JsonIgnore
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   @JsonIgnore
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   @JsonIgnore
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   @JsonIgnore
   public boolean isEnabled() {
      return true;
   }
}

