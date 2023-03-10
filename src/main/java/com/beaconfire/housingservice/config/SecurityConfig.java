package com.beaconfire.housingservice.config;

import com.beaconfire.housingservice.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
            .antMatchers("/housing-service/house/*").hasAuthority("hr")
            .antMatchers("/housing-service/facility-report").hasAuthority("hr")
            .antMatchers("/housing-service/facility-report/*").hasAuthority("hr")
            .antMatchers("/housing-service/facility-report-detail").hasAuthority("employee")
                .antMatchers("/housing-service/house/employee/all").permitAll()
            .anyRequest()
            .authenticated();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/housing-service/v2/api-docs/");
        web.ignoring().antMatchers("/housing-service/swagger.json");
        web.ignoring().antMatchers("/housing-service/swagger-ui.html");
        web.ignoring().antMatchers("/housing-service/swagger-resources/");
        web.ignoring().antMatchers("/housing-service/webjars/");
    }
}

