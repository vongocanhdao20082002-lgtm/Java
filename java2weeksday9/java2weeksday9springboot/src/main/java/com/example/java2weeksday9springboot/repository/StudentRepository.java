package com.example.java2weeksday9springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.java2weeksday9springboot.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
