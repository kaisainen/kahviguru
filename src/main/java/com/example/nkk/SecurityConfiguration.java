package com.example.nkk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();

        http.authorizeRequests()
                .antMatchers("/h2-console", "/h2-console/*").permitAll()
                .antMatchers("/", "/index").permitAll()
                .antMatchers("/images", "/images/*").permitAll()
                .antMatchers("/css", "/css/*").permitAll()
                .antMatchers("/adminpath").hasAnyRole()
                // .antMatchers("/tuotteet", "/tuotteet/*").hasAnyRole("ADMIN")
                .antMatchers("/tuotteet", "/tuotteet/*").permitAll()
                .antMatchers("/kulutustuotteet", "/kulutustuotteet/*").permitAll()
                .antMatchers("/kahvilaitteet", "/kahvilaitteet/*").permitAll()
                .anyRequest().authenticated();
        http.formLogin()
                .permitAll()
                // .defaultSuccessUrl("/tuotteet")
                .and()
                .logout().permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public DaoAuthenticationProvider authProvider() {
    // DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    // authProvider.setUserDetailsService(userDetailsService);
    // authProvider.setPasswordEncoder(passwordEncoder());
    // return authProvider;
    // }

    // @Autowired
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}
