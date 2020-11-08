package com.udemy.course.app.services;

import com.udemy.course.app.controllers.NotFoundException;
import com.udemy.course.app.models.Course;
import com.udemy.course.app.repositories.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private ICourseRepository courseRepository;


    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(BigInteger id) {
        return courseRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public void save(Course course) {
        courseRepository.save(course);
    }

    public void deleteById(BigInteger id) {
        courseRepository.deleteById(id);
    }
}
