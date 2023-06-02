package com.roky.thunderspi.security;

import com.roky.thunderspi.entities.Role;
import com.roky.thunderspi.entities.User;
import com.roky.thunderspi.util.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPrincipal implements UserDetails {

    private Long id;
    private String email;

    transient private String password;
    transient private User user;
    private Set<GrantedAuthority> authoroties;

    public static UserPrincipal createSuperUser() {
        Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(Role.ESTABLISHMENT.name()));

        return UserPrincipal.builder().id(-1L).email("system-administrator").authoroties(authorities).build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authoroties;
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
