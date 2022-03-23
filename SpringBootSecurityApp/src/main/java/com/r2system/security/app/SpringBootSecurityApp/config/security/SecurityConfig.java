package com.r2system.security.app.SpringBootSecurityApp.config.security;

import com.r2system.security.app.SpringBootSecurityApp.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

import javax.sql.DataSource;

@Component
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDetailsServiceImpl service;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

              auth.userDetailsService(service)
                      .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//             http.cors();
//             http.csrf().disable();
            // http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

             http.authorizeRequests()
                     //.antMatchers("/api/auth/**").hasRole("ADMIN")
                     .antMatchers(HttpMethod.GET,"/api/admin","/api/both").hasRole("ADMIN")
                     .antMatchers(HttpMethod.GET,"/api/member").hasRole("USER")
                     .anyRequest().authenticated()
                     .and()
                     .formLogin()
                     .and()
                     .exceptionHandling().accessDeniedPage("/error/unauthorize");

                 http
                .logout(logout -> logout
                        .logoutUrl("/basic/logout")
                        .addLogoutHandler(new SecurityContextLogoutHandler())
                );

    }


















}
