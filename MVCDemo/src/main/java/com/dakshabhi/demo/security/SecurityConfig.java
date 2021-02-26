package com.dakshabhi.demo.security;

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dakshabhi.demo.service.CustomeUserDetailsService;

@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	/*
	 * @Autowired private DataSource datasource;
	 */
	@Bean
    public UserDetailsService userDetailsService() {
        return new CustomeUserDetailsService();
    }
     
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new  BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
		
	}
	
	
	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(authenticationProvider());
	    }
	 
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests()
	            .antMatchers("/users").authenticated()
	            .anyRequest().permitAll()
	            .and()
	            .formLogin()
	                .usernameParameter("email")
	                .defaultSuccessUrl("/users")
	                .permitAll()
	            .and()
	            .logout().logoutSuccessUrl("/").permitAll();
	    }
	     
	

}
