package com.example.ip_backend.security;

import com.example.ip_backend.models.entities.UserEntity;
import com.example.ip_backend.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserEntityRepository repository;

    @Autowired
    public CustomUserDetailsService(UserEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repository.findByUsername(username);
        List<String> roles = List.of(user.getRole());
        return new User(user.getUsername(), user.getPassword(), mapRoleToAuhtorities(roles));
    }

    //potrebno je konvertovsti roles u grantedroles
    private Collection<GrantedAuthority> mapRoleToAuhtorities(List<String> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
    }
}
