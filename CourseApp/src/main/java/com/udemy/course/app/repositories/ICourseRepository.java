package com.udemy.course.app.repositories;

import com.udemy.course.app.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ICourseRepository extends JpaRepository<Course, BigInteger> {

}

