package com.example.bank;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.bank.model.User;
import com.example.bank.repository.UserRepository;

@SpringBootApplication
public class BankApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User(null, "Test User", "123456789", "test", "12345", 10.0);

		userRepository.saveAll(Arrays.asList(user));
	}

}
