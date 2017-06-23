package com.dxc.gdc.ddc.leon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(-1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	@Qualifier("customUserService")
	private UserDetailsService userDetailsService;
	
	
	
	@Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.anonymous().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .requestMatchers()
                .antMatchers("/", "/login", "/logout", "/oauth/authorize", "/oauth/confirm_access")
                .and().authorizeRequests()
                .anyRequest().authenticated()
                .and().logout().logoutUrl("/logout").
                logoutSuccessUrl("/").permitAll()
                .and()
                .csrf()
                .disable();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.daoAuthenticationProvider);

    }

    @Bean
    public DaoAuthenticationProvider getDaoAuthProvider(){
        DaoAuthenticationProvider tempProvider = new DaoAuthenticationProvider();
        tempProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        tempProvider.setUserDetailsService(this.userDetailsService);
        return tempProvider;
    }
}
