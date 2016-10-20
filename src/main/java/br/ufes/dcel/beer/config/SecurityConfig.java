package br.ufes.dcel.beer.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers("/beer/novo/**").hasRole("CADASTRAR_CERVEJA").anyRequest().authenticated()
		.and().formLogin().loginPage("/login").permitAll()
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		
		
		
	}
	
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/fonts/**")
		.and().ignoring().antMatchers("/stylesheets/**")
		.and().ignoring().antMatchers("/javascripts/**")
		.and().ignoring().antMatchers("/img/**");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication().withUser("fabiano").password("123").roles("CADASTRAR_CERVEJA")
		.and().withUser("andre").password("123").roles();
		
	}

}
