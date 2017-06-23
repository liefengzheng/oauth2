package com.dxc.gdc.ddc.leon.edge.config;

import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.logout().and().exceptionHandling()
				.authenticationEntryPoint(new Http401AuthenticationEntryPoint("Session realm=\"SESSION\""))//
				.and()//
				.antMatcher("/**").authorizeRequests()//
				.antMatchers("/webjars/**", "/", "/**/*.html", "/assets/**", "/css/**", "/js/**", "/index.html", "/home.html", "/login.html", "/uaa/**")
				.permitAll()//
				.antMatchers("/*/webjars/**").permitAll()//
				.antMatchers("/*/swagger-resources/**").permitAll()//
				.antMatchers("/*/v2/**").permitAll()//
				.antMatchers("/*/noauth/**").permitAll()//
				.antMatchers("/*/swagger-ui.html/**").permitAll()//
				.antMatchers("/*/swagger-ui.html#/**").permitAll()//
				.antMatchers("/*/*/noauth/**").permitAll()//
				.antMatchers("/*/authsec/**").permitAll()//
				.antMatchers("/*/*/authsec/**").permitAll()//
				.antMatchers("/*/api/**").permitAll()//
				.antMatchers("/*/*/api/**").permitAll()//
				.antMatchers("/uaa/logout").permitAll()
				.anyRequest().authenticated()//
				.and()//
				.logout().logoutUrl("/uaa/logout").logoutSuccessUrl("/")//
				.clearAuthentication(true).deleteCookies("JSESSIONID")//
				.invalidateHttpSession(true).permitAll()
				.and()
				.csrf().disable();  //disable csrf in order to check token
	}


}

