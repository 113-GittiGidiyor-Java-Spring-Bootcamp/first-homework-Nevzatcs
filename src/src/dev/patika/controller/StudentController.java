package dev.patika.controller;

import dev.patika.models.Course;
import dev.patika.models.Student;
import dev.patika.service.StudentService;
import dev.patika.utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import java.util.List;
// controller - service relations
public class StudentController {
    private StudentService studentService = new StudentService();
    EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");
    public Student findStudent(int stuID){
        return studentService.findById(stuID);
    }

    public List<Student> findAllStudents(){
        return studentService.findAll();
    }

    public void saveStudent(Student student){
        studentService.saveToDatabase(student);
    }
    public void deleteStudent(String name){
        studentService.deleteStudentFromDatabase(name);
    }

    public List<Course> findCourseOfStudent(String name){
        return null;
    }

    public void updateStudent(Student student, int id){
        studentService.updateOnDatabase(student, id);
    }
}
