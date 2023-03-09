package com.nm.Militaryoffice.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class User implements UserDetails {
    @Positive
    private Long id;
    @Size(min = 5, max = 20)
    @NotEmpty
    private String password;
    @Size(min = 3, max = 30)
    @NotEmpty
    private String email;
    @Size(min = 3, max = 30)
    private String passwordConfirmation;
    private String name;
    private String patronymic;
    private String surname;
    private Boolean isLocked;
    private UserRole userRole = UserRole.NOT_SPECIFIED;

    public User(String password,
                String email,
                String passwordConfirmation,
                Boolean isLocked,
                UserRole userRole) {
        this.password = password;
        this.email = email;
        this.passwordConfirmation = passwordConfirmation;
        this.isLocked = isLocked;
        this.userRole = userRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(userRole.name());

        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
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
