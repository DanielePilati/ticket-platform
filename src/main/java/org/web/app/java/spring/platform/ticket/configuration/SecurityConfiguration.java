package org.web.app.java.spring.platform.ticket.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.web.app.java.spring.platform.ticket.service.DatabaseUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/tickets/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/notes/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/users/edit/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/users/changestatus").hasAnyAuthority("USER","ADMIN")
                .requestMatchers("/users/**").hasAnyAuthority("ADMIN")
                .requestMatchers("/types/**").hasAnyAuthority("ADMIN")
                .requestMatchers("/**").permitAll())
        .formLogin((login) -> login
                .loginPage("/")
                .loginProcessingUrl("/process-login")
                .defaultSuccessUrl("/login/success_handler")
                .failureForwardUrl("/login/failure_handler"))
        .logout((logout) -> logout
        		.logoutSuccessUrl("/")
        		.logoutUrl("/login/logout"));
 

		return http.build();
	}

	@Bean
	DatabaseUserDetailService userDetailService() {
		return new DatabaseUserDetailService();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	DaoAuthenticationProvider AuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailService());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	

}
