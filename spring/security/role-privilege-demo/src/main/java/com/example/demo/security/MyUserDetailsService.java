package com.example.demo.security;

import com.example.demo.dto.Privilege;
import com.example.demo.dto.Role;
import com.example.demo.dto.User;
import com.example.demo.event.SetupDataLoader;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service("userDetailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public MyUserDetailsService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(null == user) {
            return new org.springframework.security.core.userdetails.User(" ", " ", true, true, true, true, getAuthorities(Collections.singletonList(roleRepository.findByName(SetupDataLoader.ROLE_NAME_USER))));
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, getAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {
        List<Privilege> privilegeList = new ArrayList<>();
        for(Role role: roles) {
            privilegeList.addAll(role.getPrivileges());
        }
        return privilegeList.stream().map(privilege -> privilege.getName()).distinct().collect(Collectors.toList());
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        Objects.requireNonNull(privileges);
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(String privilege: privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
