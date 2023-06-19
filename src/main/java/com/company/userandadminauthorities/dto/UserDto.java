package com.company.userandadminauthorities.dto;

import com.company.userandadminauthorities.model.Authorities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto implements UserDetails {

    private Integer userId;
    private String surname;
    private String name;
    private String middleName;
    private String email;
    private String emailPassword;
    private String phoneNumber;
    private Integer birthday;
    private Integer birthMonth;
    private Integer birthYear;
    private String role;


    private String username;
    private String password;
    private Boolean enabled;

    private Set<Authorities> authority;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @JsonIgnore
    public Set<Authorities> getAuthority() {
        return authority;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(authority)
                .map(auth -> auth.stream()
                        .map(a -> new SimpleGrantedAuthority(a.getAuthority()))
                        .toList())
                .orElse(new ArrayList<>());
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
        return enabled;
    }


}
