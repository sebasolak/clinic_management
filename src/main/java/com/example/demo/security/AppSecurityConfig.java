package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.demo.security.AppPermissions.*;
import static com.example.demo.security.AppRole.*;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/**").hasAnyAuthority(DOCTOR_READ.getPermission(), CHIEF_READ.getPermission())
                    .antMatchers(HttpMethod.POST, "/api/**").hasAuthority(CHIEF_WRITE.getPermission())
                    .antMatchers(HttpMethod.PUT, "/api/**").hasAnyAuthority(DOCTOR_WRITE.getPermission(), CHIEF_WRITE.getPermission())
                    .antMatchers(HttpMethod.DELETE, "/api/**").hasAnyAuthority(DOCTOR_WRITE.getPermission(), CHIEF_WRITE.getPermission())
                        .antMatchers(HttpMethod.GET,"/management/api/**").hasAuthority(CHIEF_READ.getPermission())
                        .antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority(CHIEF_READ.getPermission())
                        .antMatchers(HttpMethod.PUT,"/management/api/**").hasAuthority(CHIEF_READ.getPermission())
                        .antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(CHIEF_READ.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
//                .formLogin();
               // .defaultSuccessUrl("/", true);
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails chiefKelso = User.builder()
                .username("b.kelso")
                .password(passwordEncoder().encode("password"))
                .authorities(CHIEF_OF_MEDICINE.getGrantedAuthorities())
                .build();

        UserDetails docCarroll = User.builder()
                .username("s.carroll")
                .password(passwordEncoder().encode("password"))
                .authorities(DOC.getGrantedAuthorities())
                .build();

        UserDetails docHernandez = User.builder()
                .username("p.hernandez")
                .password(passwordEncoder().encode("password"))
                .authorities(DOC.getGrantedAuthorities())
                .build();

        UserDetails docSimpson = User.builder()
                .username("a.simpson")
                .password(passwordEncoder().encode("password"))
                .authorities(DOC.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                chiefKelso,
                docCarroll,
                docHernandez,
                docSimpson
        );
    }
}
