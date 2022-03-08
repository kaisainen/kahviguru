package com.example.nkk;

import com.example.nkk.services.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // poistetaan csrf-tarkistus käytöstä h2-konsolin vuoksi
        http.csrf().disable();
        // sallitaan framejen käyttö
        http.headers().frameOptions().sameOrigin();

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/images", "/images/*").permitAll()
                .antMatchers("/css", "/css/*").permitAll()
                .antMatchers("/admin", "/admin/*").hasAnyRole("ADMIN")
                // .antMatchers("/vip").hasAnyRole("ADMIN", "VIP")
                .antMatchers(HttpMethod.GET, "/kulutustuotteet", "/kulutustuotteet/*").permitAll()
                .antMatchers(HttpMethod.GET, "/kahvilaitteet", "/kahvilaitteet/*").permitAll();

        // .anyRequest().authenticated();
        http.formLogin()
                .permitAll();
        // .and()
        // .logout()
        // .logoutUrl("/logout")
        // .logoutSuccessUrl("/login");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws
    // Exception {
    // auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    // }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder((passwordEncoder()));
        return authProvider;
    }
}
