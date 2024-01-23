
package com.capstoneproject.educonnect.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.capstoneproject.educonnect.Fillter.AuthenticateRequestFilter;
import com.capstoneproject.educonnect.Handler.AuthenEntryPoint;
import com.capstoneproject.educonnect.Handler.AuthenFailureHandler;
import com.capstoneproject.educonnect.Handler.AuthenSuccessHandler;
import com.capstoneproject.educonnect.Service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticateRequestFilter authenticateRequestFilter;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private AuthenEntryPoint authenEntryPoint;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeHttpRequests()
				.antMatchers("/**").permitAll().anyRequest().authenticated().and()
				.exceptionHandling().authenticationEntryPoint(authenEntryPoint).and()
				.logout((logout) -> logout.permitAll());

		http.addFilterBefore(authenticateRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService).passwordEncoder(encoder());
	}


}