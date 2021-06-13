package com.manics.rest.config;

import com.manics.rest.config.jwt.JwtConfig;
import com.manics.rest.config.jwt.JwtTokenVerifier;
import com.manics.rest.config.jwt.JwtUserAuthenticationFilter;
import com.manics.rest.config.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImp userDetailsService;
    private final JwtConfig jwtConfig;
    private final JwtUtils jwtUtils;

    @Autowired
    public WebSecurityConfig(PasswordEncoder passwordEncoder,
                             UserDetailsServiceImp userDetailsService,
                             JwtConfig jwtConfig,
                             JwtUtils jwtUtils) {

        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.jwtConfig = jwtConfig;
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No crear cookie
                .and().csrf().disable()
                .httpBasic().disable() // Authorization: Basic base64(usuario:contrasena) x.x
                .authorizeRequests().antMatchers("/login", "/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtUserAuthenticationFilter(authenticationManager(), jwtConfig, jwtUtils))
                .addFilterAfter(new JwtTokenVerifier(jwtUtils), JwtUserAuthenticationFilter.class);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

}