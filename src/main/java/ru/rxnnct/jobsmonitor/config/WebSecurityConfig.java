package ru.rxnnct.jobsmonitor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${adminPanelUsername}")
    String adminPanelUsername;
    @Value("${adminPanelPassword}")
    String adminPanelPassword;

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/api/jobs-qty").permitAll()
                .antMatchers("/admin**").hasRole("ADMIN")
//                .antMatchers("/api/admin/").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception
    {
        auth.inMemoryAuthentication()
                .withUser(adminPanelUsername)
                .password("{noop}" + adminPanelPassword)
                .roles("ADMIN");
    }

}
