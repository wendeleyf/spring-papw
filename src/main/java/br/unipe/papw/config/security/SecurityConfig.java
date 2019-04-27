package br.unipe.papw.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			http.csrf().disable().authorizeRequests()
			.antMatchers("/css/**", "/index").permitAll()
			.antMatchers("/user/**").hasRole("USER")
			.antMatchers("/user/**").hasRole("admin")
			.antMatchers("/admin/**").hasRole("admin")
			.anyRequest().authenticated()
			.and().formLogin().permitAll()
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
			
		}
		
		
	  @Override
	  public void configure(AuthenticationManagerBuilder builder) throws Exception {
	    builder
	    .inMemoryAuthentication()
	    .withUser("user").password("{noop}123").roles("USER")
	    .and()
	    .withUser("fuji").password("{noop}123").roles("admin")
	    .and()
	    .withUser("wendeley").password("{noop}123").roles("admin");

		  
	  }
	
}
