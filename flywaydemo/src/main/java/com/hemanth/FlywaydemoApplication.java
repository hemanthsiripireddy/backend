package com.hemanth;

import com.hemanth.model.Book;
import com.hemanth.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlywaydemoApplication implements CommandLineRunner {
	@Autowired
	private  BookRepo bookRepo;

	public static void main(String[] args) {
		SpringApplication.run(FlywaydemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (Book book : bookRepo.findAll()) {
			System.out.println(book.toString());

		}

	}
}
