package org.perscholas.freelance.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// TODO redo security config

// This is temporarily copied over from another project to override the default Spring security login page

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                //these are unsecured paths
                .antMatchers("/pub/**","/error/**","/login/**","/search").permitAll()
                //these are secured paths
                .antMatchers("/admin/**","/user/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/login/login")
                .loginProcessingUrl("/login/loginSecurityPost")
                .and()
                .logout()
                .invalidateHttpSession(true)
                .logoutUrl("/login/logout")
                .logoutSuccessUrl("/login/logoutSuccess")
                .and()
                .rememberMe()
                .key("SR_KEY")
                .tokenValiditySeconds(60 * 60 * 24 * 2)
                .rememberMeParameter("remember-me")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/error/404");
    }



}