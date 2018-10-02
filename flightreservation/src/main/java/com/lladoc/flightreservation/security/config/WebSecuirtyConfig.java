package com.lladoc.flightreservation.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecuirtyConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/showReg", "/", "/index.html", "/registerUser", "/loginSubmit", "/login", "/login/*", "login/login", "/reservations/*")
				.permitAll().antMatchers("/admin/showAddFlight").hasAnyAuthority("ADMIN").anyRequest().authenticated()
				.and().csrf().disable();
	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

}
