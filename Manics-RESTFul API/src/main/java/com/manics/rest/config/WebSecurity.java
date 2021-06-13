package com.manics.rest.config;

import com.manics.rest.service.UsuarioService;

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
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final TokenFilter tokenFilter;
    private final CustomUserDetailsService userDetailsService;
    private final UsuarioService usuarioService;
    private final CustomLogoutHandler customLogoutHandler;

    @Autowired
    public WebSecurity(PasswordEncoder passwordEncoder, TokenFilter tokenFilter,
            CustomUserDetailsService userDetailsService, UsuarioService usuarioService,
            CustomLogoutHandler customLogoutHandler) {

        this.passwordEncoder = passwordEncoder;
        this.tokenFilter = tokenFilter;
        this.userDetailsService = userDetailsService;
        this.usuarioService = usuarioService;
        this.customLogoutHandler = customLogoutHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No crear cookie
                .and().csrf().disable().httpBasic().disable() // Authorization: Basic base64(usuario:contrasena) x.x
                .authorizeRequests().antMatchers("/login", "/register").permitAll().anyRequest().authenticated().and()
                .logout().logoutUrl("/logout").addLogoutHandler(customLogoutHandler).and()
                .addFilter(new UsuarioAuthenticationFilter(authenticationManager(), usuarioService))
                .addFilterBefore(tokenFilter, BasicAuthenticationFilter.class);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

}