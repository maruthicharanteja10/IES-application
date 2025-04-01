package com.sb.majorproject.security;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("tej123").roles("ADMIN").build();
//
//	
//
//		return new InMemoryUserDetailsManager(admin);
//	}
//
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests(auth -> auth.requestMatchers("/IES/admin/**").hasRole("ADMIN") // Admin-only pages
//				.requestMatchers("/IES/**").authenticated() // All authenticated users
//				.anyRequest().permitAll()).formLogin().and().logout().logoutUrl("/logout")
//				.logoutSuccessUrl("/login?logout").permitAll();
//
//		return http.build();
//	}
}
