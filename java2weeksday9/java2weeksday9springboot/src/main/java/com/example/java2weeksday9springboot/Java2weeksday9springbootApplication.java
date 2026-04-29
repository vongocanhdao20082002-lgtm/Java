package com.example.java2weeksday9springboot;

import com.example.java2weeksday9springboot.entity.Student;
import com.example.java2weeksday9springboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Java2weeksday9springbootApplication implements CommandLineRunner {

	@Autowired
	private StudentRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(Java2weeksday9springbootApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Student s = new Student();
		s.setStudentName("An");
		s.setAge(20);
		s.setEmail("an@gmail.com");

		repo.save(s);
		System.out.println("Insert OK");
	}
}