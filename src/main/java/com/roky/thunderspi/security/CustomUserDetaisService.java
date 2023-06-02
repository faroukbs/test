package com.roky.thunderspi.security;

import com.roky.thunderspi.entities.User;
import com.roky.thunderspi.services.UserServiceImpl;
import com.roky.thunderspi.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomUserDetaisService implements UserDetailsService {

    @Autowired
    private UserServiceImpl userService;


    private User user;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username) );
        Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(user.getRole().name()));

        return UserPrincipal.builder().user(user).id(user.getId()).email(username).password(user.getPassword()).authoroties(authorities).build();
    }

    public boolean isEnabled() {
        return user.isEnabled();
    }
}
