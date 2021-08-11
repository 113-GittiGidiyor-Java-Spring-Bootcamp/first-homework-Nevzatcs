package dev.patika.controller;

import dev.patika.models.Course;
import dev.patika.service.CourseService;
import dev.patika.utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class CourseController {
    private CourseService courseService = new CourseService();
    EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");
    public Course findStudent(int courseID){
        return courseService.findById(courseID);
    }

    public List<Course> findAllCourses(){
        return courseService.findAll();
    }

    public void saveCourse(Course course){
        courseService.saveToDatabase(course);
    }
    public void deleteCourse(int id){
        courseService.deleteCourseFromDatabase(id);
    }
}
