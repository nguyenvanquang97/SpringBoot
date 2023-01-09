package com.example.bookbackend;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookBackendApplication.class, args);
	}

	@Bean
	public Faker faker() {
		return new Faker();
	}

	@Bean
	public Slugify slugify() {
		return Slugify.builder()
				.lowerCase(true)
				.customReplacement("đ", "d")
				.customReplacement("Đ", "d")
				.build();
	}

}
