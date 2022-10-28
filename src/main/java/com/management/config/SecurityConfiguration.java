package com.management.config;

import com.management.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    private final UserServiceImpl userService;

    SecurityConfiguration(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().antMatchers("/authenticated/**").authenticated()
                .antMatchers("/company/**").hasAuthority("HR")
//                .antMatchers("/company/**").permitAll()
                .antMatchers("/user/**").hasAnyAuthority("DEVELOPER", "HR")
                .and()
//                .authorizeRequests().antMatchers("api/v1/user/register").permitAll()
//                .anyRequest().authenticated()
//                .and()
                .formLogin()
                .and()
                .logout().logoutUrl("/logout");
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }
}
