package com.thinkpalm.ecommerceApp.Model;

import com.thinkpalm.ecommerceApp.Validator.EmailValid;
import com.thinkpalm.ecommerceApp.Validator.NameValid;
import com.thinkpalm.ecommerceApp.Validator.PasswordValid;
import com.thinkpalm.ecommerceApp.Validator.PhoneNumbervalid;
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

@NameValid
    private String name;

    @Column(unique = true, nullable = false)
    @EmailValid
    private  String email;

@PasswordValid
    private String password;

@PhoneNumbervalid

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
