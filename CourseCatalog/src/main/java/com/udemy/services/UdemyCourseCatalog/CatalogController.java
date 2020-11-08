package com.udemy.services.UdemyCourseCatalog;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CatalogController {

    @Autowired
    EurekaClient client;
    @RequestMapping("/")
    @HystrixCommand(fallbackMethod = "displayDefaultHome")
    public String getCatalogHome(){
        String courseAppMessage = "";
        //String courseAppURL = "http://localhost:8080/courses";
        RestTemplate restTemplate = new RestTemplate();
        InstanceInfo instanceInfo = client.getNextServerFromEureka("udemy-course-service", false);
        String courseAppURL = instanceInfo.getHomePageUrl();
        courseAppMessage = restTemplate.getForObject(courseAppURL, String.class);
        return ("Welcome to Udemy's Course Catalog " + courseAppMessage);
    }

    public String displayDefaultHome(){
        return ("Welcome to Udemy's Course Catalog "+"Please try after some time");
    }

    @RequestMapping("/catalog")
    public String getCatalog(){
        String courses = "";
        //String courseAppURL = "http://localhost:8080/courses";
        InstanceInfo instanceInfo = client.getNextServerFromEureka("udemy-course-service", false);
        String courseAppURL = instanceInfo.getHomePageUrl();
        courseAppURL = courseAppURL+"/courses";
        RestTemplate restTemplate = new RestTemplate();
        courses = restTemplate.getForObject(courseAppURL, String.class);
        return ("Our courses are: " + courses);
    }

    @RequestMapping("/firstcourse")
    public String getSpecificCourse(){
        Course course = new Course();
        User user = new User();
        //String courseAppURL = "http://localhost:8080/1";
        InstanceInfo instanceInfo = client.getNextServerFromEureka("udemy-course-service", false);
        String courseAppURL = instanceInfo.getHomePageUrl();
        courseAppURL = courseAppURL+"/1";
        RestTemplate restTemplate = new RestTemplate();
        course = restTemplate.getForObject(courseAppURL, Course.class);

        instanceInfo = client.getNextServerFromEureka("udemy-user-service", false);

        String userAppURL = instanceInfo.getHomePageUrl();
        userAppURL =  userAppURL+ "/" + course.getCourseid();

        String userList = restTemplate.getForObject(userAppURL, String.class);

        return ("Our first course is: " + course.getCoursename() + "**** and Enrolled users are: " + userList);
    }
}
