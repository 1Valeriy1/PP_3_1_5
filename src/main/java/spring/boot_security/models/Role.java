package spring.boot_security.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    public Role() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    @Override
    @JsonIgnore
    public String getAuthority() {
        return role;
    }

    public Long getId() {
        return id;
    }
    @JsonIgnore
    public String getRoleWithoutPrefix() {
        return role.substring("ROLE_".length());
    }

    @Override
    public String toString() {
        return role;
    }
}

