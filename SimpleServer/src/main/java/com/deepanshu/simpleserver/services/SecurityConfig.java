package com.deepanshu.simpleserver.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import com.deepanshu.simpleserver.database.redisDB.UserAuthData;
import com.deepanshu.simpleserver.database.redisDB.UserAuthService;
import com.deepanshu.simpleserver.database.redisDB.UserRepository;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig{
	
	@Autowired
	UserRepository usrRepo;
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
//				.authenticationProvider(authenticationProvider())
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
				registry.requestMatchers("SignIn.html", "/BgImage.jpg", "/favicon.jpg", "/css/**", "/images/**").permitAll();
				registry.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN");
				registry.requestMatchers("/**").hasAnyRole("USER", "ADMIN");
				registry.anyRequest().authenticated();
			})
			.build();
	}
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//      return new BCryptPasswordEncoder();
//	}
}
	
//	private final UserAuthService appUserService;
//
//    public SecurityConfig(UserAuthService appUserService) {
//        this.appUserService = appUserService;
//    }
//	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		return appUserService;
//	}
//	
//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//	    return config.getAuthenticationManager();
//	}
//	
//	@Bean
//	public AuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setUserDetailsService(appUserService);
//		provider.setPasswordEncoder(passwordEncoder());
//		return provider;
//	}
//	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
	
//	private UserAuthService userAuthService;
////	@Override
//	protected void configure(HttpSecurity http) throw Exception{
//		http .authorizeRequest()
//				.antMatchers("/login").permitAll()
//				.anyRequest().authenticated()
//				.and() 
//					.fromLogin().LoginPage("/login")
//				.and() 
//					.logout().permitAll();
//	}
//	
////	@Override
//	protected void congigure(AuthenticationManagerBuilder auth) {
//		auth.userDetailsService(userAuthService).passwordEncoder(new BCryptPasswordEncoder());
//	}
	

//	@Bean
//	public UserDetailsService userDetailsService(String username) {
//		Optional<UserAuthData> user = usrRepo.findById(username);
//		if(user.isPresent()) {
//			var userObj = user.get();
//			return new InMemoryUserDetailsManager( User.builder()
//				   .username(username)
//				   .password(userObj.getPassword())
//				   .roles(userObj.getRole())
//				   .build() );	   
//		}
//		return new InMemoryUserDetailsManager();
//	}

//	"/*", "/*.js"

//
//    // Used by Spring internally for authentication
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//        return authConfig.getAuthenticationManager();
//    }




//.csrf().disable()
//.authorizeHttpRequests(auth -> auth
//    .requestMatchers("/signin", "/signup", "/style.css", "/user/g-login", "/storeData.html",
//            "/fonts/**", "/BgImage.jpg","login.html","home.html").permitAll()
//    .requestMatchers("/", "/user/**").hasAuthority("Admin")
//    .anyRequest().authenticated()
//)
//.formLogin(form -> form
//    .loginPage("/signin")
//    .defaultSuccessUrl("/user/get", true)
//    .permitAll()
//)
//.logout(logout -> logout
//    .logoutUrl("/logout")
//    .logoutSuccessUrl("/signin")
//    .permitAll()
//);
