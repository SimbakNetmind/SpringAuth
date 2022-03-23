package com.r2system.security.app.SpringBootSecurityApp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringBootSecurityAppApplication  implements CommandLineRunner {

//   @Bean
//	public PasswordEncoder passwordEncoder(){
//		return  new BCryptPasswordEncoder();
//	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String password = "12345";

		for(int i=0; i<2; i++) {
			String bcryptPassword = passwordEncoder().encode(password);
			System.out.println(bcryptPassword);
		}

	}
}
