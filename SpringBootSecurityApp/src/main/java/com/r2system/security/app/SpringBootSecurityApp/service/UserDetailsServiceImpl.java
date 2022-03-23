package com.r2system.security.app.SpringBootSecurityApp.service;


import com.r2system.security.app.SpringBootSecurityApp.model.Role;
import com.r2system.security.app.SpringBootSecurityApp.model.Users;
import com.r2system.security.app.SpringBootSecurityApp.repository.IUserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

//@Service("userDetailService")
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

   // private Logger logger =LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private IUserRepository repository;

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Users> _user = repository.findByUsername(username);

        if(_user == null) {
           // logger.error("Error en el Login: no existe el usuario '" + username + "' en el sistema!");
            throw new UsernameNotFoundException("Username: " + username + " no existe en el sistema!");
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for(Role role: _user.get().getRoles()) {
          //  logger.info("Role: ".concat(role.getAuthority()));
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }

        if(authorities.isEmpty()) {
           // logger.info("Error en el Login: Usuario '" + username + "' no tiene roles asignados!");

            throw new UsernameNotFoundException("Error en el Login: usuario '" + username + "' no tiene roles asignados!");
        }

        return new User(_user.get().getUsername(), _user.get().getPassword(),_user.get().getEnabled(),
                true, true, true, authorities);
    }













}
