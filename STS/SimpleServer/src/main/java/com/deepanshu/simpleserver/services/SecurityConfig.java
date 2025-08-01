package com.deepanshu.simpleserver.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import com.deepanshu.redisLocal.redisDB.*;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig{
	
	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepo) {
		List<UserDetails> users = new ArrayList<>();
		
		for (UserAuthData user : userRepo.findAll()) {
			UserDetails details = User
				.withUsername(user.getUsername())
				.password("{noop}" + user.getPassword()) // use encoded version in prod
				.roles(user.getRole())
				.build();
			users.add(details);
		}

		return new InMemoryUserDetailsManager(users);
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		return http
				.csrf(AbstractHttpConfigurer::disable)
			.formLogin(httpForm -> httpForm
					.loginPage("/signIn")
					.loginProcessingUrl("/signIn")
					.defaultSuccessUrl("/signIn?success=true", true)
					.failureUrl("/signIn?error=true")
					.permitAll()
			)
			.logout(logout -> logout
					.logoutUrl("/logout")
					.logoutSuccessUrl("/signIn?logout=true")
					.permitAll()
				)
			.authorizeHttpRequests(registry -> {
				registry.requestMatchers("/signUp", "SignUp.html", "SignIn.html", "/BgImage.jpg", "/favicon.jpg", "/css/**", "/images/**", "/").permitAll();
				registry.requestMatchers("/addData.html").hasRole("ADMIN");
				registry.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN");
				registry.anyRequest().authenticated();
			})
			.build();
	}
}