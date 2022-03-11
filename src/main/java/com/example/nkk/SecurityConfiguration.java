package com.example.nkk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();

        http.authorizeRequests()
                // .requestMatchers(org.springframework.boot.autoconfigure.security.servlet.PathRequest.toStaticResources()
                // .atCommonLocations())
                // .permitAll()
                .antMatchers("/h2-console", "/h2-console/**").permitAll()
                .antMatchers("/", "/index").permitAll()
                .antMatchers("/images", "/images/**").permitAll()
                .antMatchers("/css", "/css/**").permitAll()
                .antMatchers("/tuotteet", "/tuotteet/**").permitAll()
                .antMatchers("/admin", "/admin/**").permitAll()
                .antMatchers("/vip").hasAnyRole("ADMIN", "VIP")
                .antMatchers("/kulutustuotteet", "/kulutustuotteet/**").permitAll()
                .antMatchers("/kahvilaitteet", "/kahvilaitteet/*").permitAll()
                .anyRequest().authenticated();
        http.formLogin()
                .permitAll()
                .defaultSuccessUrl("/tuotteet")
                .and()
                .logout().permitAll();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
