package com.thinkpalm.ecommerceApp.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customer")
public class Customer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

@NotNull(message = "Username cannot be empty")
@NotEmpty
    private String name;

    @Column(unique = true, nullable = false)
@NotNull
    @Email(message = "please provide a valid email id")
    private  String email;
@Size(min=6,max = 15)
@NotNull(message = "password must contain atleast 6 characters")

    private String password;

@Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number format. Please provide a 10-digit integer phone number.")

    private String phone_number;

@NonNull
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp created_at;


    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp modified_at;

 @ManyToOne
@JoinColumn(name = "role_id")
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {;
        return role.getRoleName().getAuthorities();
    }

    @Override
    public String getUsername() {

        return email;
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
