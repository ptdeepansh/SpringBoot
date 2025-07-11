//package com.deepanshu.simpleserver.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.SecurityFilterChain;
//
//import com.deepanshu.simpleserver.services.redisDB.UserAuthService;
//
//import lombok.AllArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@AllArgsConstructor
//@EnableWebSecurity
//public class SecurityConfig{
//	
////	@Bean
////	public UserDetailsService userDetailsService() {
////		UserDetails user = User.withUsername("Deepanshu")
////				.password(passwordEncoder().encode("password123"))
////				.roles("Admin")
////				.build();
////		return new InMemoryUserDetailsManager(user);
////	}
//	
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
//	
////	private UserAuthService userAuthService;
//////	@Override
////	protected void configure(HttpSecurity http) throw Exception{
////		http .authorizeRequest()
////				.antMatchers("/login").permitAll()
////				.anyRequest().authenticated()
////				.and() 
////					.fromLogin().LoginPage("/login")
////				.and() 
////					.logout().permitAll();
////	}
////	
//////	@Override
////	protected void congigure(AuthenticationManagerBuilder auth) {
////		auth.userDetailsService(userAuthService).passwordEncoder(new BCryptPasswordEncoder());
////	}
//	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//		return http
//				.csrf(AbstractHttpConfigurer::disable)
//				.authenticationProvider(authenticationProvider())
//			.formLogin(httpForm -> {
//				httpForm
//					.loginPage("/signIn")
//					.defaultSuccessUrl("/user/all")
//					.permitAll();
//			})
//			.authorizeHttpRequests(registry -> {
//				registry.requestMatchers("/user/g-login","/signUp","SignUp.html","SignIn.html","/style.css","/BgImage.jpg","/").permitAll();
//				registry.requestMatchers("/user/**").hasRole("USER");
//				registry.anyRequest().authenticated();
//			})
//			.build();
//	}
//}
////	"/*", "/*.js"
////	@Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////    // Used by Spring internally for authentication
////    @Bean
////    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
////        return authConfig.getAuthenticationManager();
////    }
//
//
//
//
////.csrf().disable()
////.authorizeHttpRequests(auth -> auth
////    .requestMatchers("/signin", "/signup", "/style.css", "/user/g-login", "/storeData.html",
////            "/fonts/**", "/BgImage.jpg","login.html","home.html").permitAll()
////    .requestMatchers("/", "/user/**").hasAuthority("Admin")
////    .anyRequest().authenticated()
////)
////.formLogin(form -> form
////    .loginPage("/signin")
////    .defaultSuccessUrl("/user/get", true)
////    .permitAll()
////)
////.logout(logout -> logout
////    .logoutUrl("/logout")
////    .logoutSuccessUrl("/signin")
////    .permitAll()
////);
//
//
