package com.udemy.course.app.controllers;

import com.udemy.course.app.repositories.ICourseRepository;
import com.udemy.course.app.models.Course;
import com.udemy.course.app.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
public class CourseController {

    @Autowired

    private CourseService courseService;

    @RequestMapping("/")
    public String getCourseAppHome(){

        return ("CourseApp home");
    }


    @RequestMapping("/courses")
    public List<Course> getCourses(){

        return courseService.findAll();
            }


    @RequestMapping("/{id}")
    public Course getById(@PathVariable("id") BigInteger id){
        return courseService.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value="/courses")
    public void saveCourse(@RequestBody Course course){
        courseService.save(course);
    }
    @RequestMapping(method = RequestMethod.DELETE, value="{id}")
    public void deleteCourse(@PathVariable BigInteger id) {
        courseService.deleteById(id);
    }
}
