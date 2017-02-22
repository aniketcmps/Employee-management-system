package com.freedom.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	/**
	 * Security Configuration
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.POST, "/rest/**").authenticated()
			.antMatchers(HttpMethod.PUT, "/rest/**").authenticated()
			.antMatchers(HttpMethod.DELETE, "/rest/**").authenticated()
			.antMatchers(HttpMethod.GET, "/rest/**").authenticated()
			.antMatchers(HttpMethod.PATCH, "/rest/**").authenticated()
			.anyRequest().permitAll().and()
			.httpBasic().and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}