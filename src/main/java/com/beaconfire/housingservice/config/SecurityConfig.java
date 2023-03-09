package com.beaconfire.housingservice.config;

import com.beaconfire.housingservice.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//WebSecurityConfigurerAdapter needs to extended to override some of its methods
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private JwtFilter jwtFilter;

    @Autowired
    public void setJwtFilter(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
            .antMatchers("/house/*").hasAuthority("hr")
            .antMatchers("/facility-report").hasAuthority("employee")
            .antMatchers("/facility-report/*").hasAuthority("hr")
            .antMatchers("/facility-report-detail").hasAuthority("employee")
            .anyRequest()
            .authenticated();
    }
}

