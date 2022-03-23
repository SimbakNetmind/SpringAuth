package com.r2system.security.app.SpringBootSecurityApp.config.security;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Component
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

//    @AutoConfigureOrder
//    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserBuilder users = User.builder().passwordEncoder(encoder::encode);


        auth.inMemoryAuthentication()
                .withUser(users.username("simbak").password("123123").roles("ADMIN"))
                .withUser(users.username("juan").password("123123").roles("MEMBER"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//             http.cors();
//             http.csrf().disable();
            // http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

             http.authorizeRequests()
                     //.antMatchers("/api/auth/**").hasRole("ADMIN")
                     .antMatchers(HttpMethod.GET,"/api/admin").hasRole("ADMIN")
                     .antMatchers(HttpMethod.GET,"/api/member").hasRole("MEMBER")
                     //.antMatchers(HttpMethod.GET,"/api/swagger-ui/**").permitAll()
                     .anyRequest().authenticated()
                     .and()
                     .formLogin()
                     .and()
                     .exceptionHandling().accessDeniedPage("/error/unauthorize");

    }


















}
