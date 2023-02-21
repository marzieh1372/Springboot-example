package com.example.bank.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * This class extends WebSecurityConfigurerAdapter class.
 */

@Configuration
@EnableWebSecurity(debug= true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService jwtUserDetailsService;
  @Autowired
  private JwtTokenFilter jwtTokenFilter;

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf().disable()
            .authorizeRequests().antMatchers("/cutomer/**").permitAll().anyRequest()
            .authenticated();

    http.addFilter(jwtTokenFilter);

  }
}
