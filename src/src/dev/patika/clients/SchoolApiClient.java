package dev.patika.clients;

import dev.patika.controller.CourseController;
import dev.patika.controller.StudentController;
import dev.patika.models.*;
import dev.patika.utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
// main method for test
public class SchoolApiClient {
    public static void main(String[] args) {

        if(checkTestData() == 0){
            saveTestData();
        }

        StudentController controller = new StudentController();
        CourseController control = new CourseController();

        Course course4 = new Course("Math", 3001,4);
        Student student4 = new Student("Can Ulusoy", (LocalDate.of(1995, Month.JULY,8)),"Istanbul","Male");


        /* Tested saving new student */
        //controller.saveStudent(student4);
        /* Tested updating new student */
        //student4.setS_address("Edirne");
        //student4.setS_birthDate(LocalDate.of(1994, Month.JULY,8));
        //controller.updateStudent(student4,4);

        /* Tested deleting new student */
        //controller.deleteStudent("Can Ulusoy");

        /* Tested saving new course */
        //control.saveCourse(course4);

        /* Tested updating new course */
        //course4.setCourseName("English");
        //course4.setCourseCode(1003);
        //course4.setCreditScore(3);
        //control.updateCourse(course4,4);

        /* Tested deleting new course */
        //control.deleteCourse(4);

        /* Tested finding all students */
        List<Student> returnedList = controller.findAllStudents();
        for (Student student : returnedList) {
                System.out.println(student);
          }
        /* Tested finding all courses*/
        List<Course> returnList = control.findAllCourses();
        for (Course course:returnList) {
            System.out.println(course);
        }

    }
    private static int checkTestData() {
        EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");
        return em.createQuery("from Student", Student.class).getResultList().size();
    }

    private static void saveTestData(){

        Student student1 = new Student("Nevzat Samur", (LocalDate.of(1997, Month.MAY,04)),"Istanbul","Male");
        Student student2 = new Student("Ali Gündüz", (LocalDate.of(1999, Month.JANUARY,15)),"Ankara","Male");
        Student student3 = new Student("Ayşe Fatma", (LocalDate.of(1998, Month.AUGUST,24)),"İzmir","Female");

        Course course1 = new Course("Physics", 1001, 4);
        Course course2 = new Course("Geometry", 2001, 4);
        Course course3 = new Course("Biology", 1002, 3);

        // infos for db
        student1.getStudentCourse().add(course1);
        student1.getStudentCourse().add(course2);

        student2.getStudentCourse().add(course1);
        student2.getStudentCourse().add(course2);
        student2.getStudentCourse().add(course3);

        student3.getStudentCourse().add(course3);


        Instructor permanentInstructor = new PermanentInstructor("Mehmet Telli", "Ankara", "05331112233",2500);
        Instructor visitingResearcher = new VisitingResearcher("Veli Akkuş", "Sakarya", "05344445566",20);
        Instructor permanentInstructor1 = new PermanentInstructor("Koray", "Istanbul", "05357778899",2800);


        course1.setInstructor(permanentInstructor);
        course2.setInstructor(visitingResearcher);
        course3.setInstructor(permanentInstructor1);

        EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");
        try {
            em.getTransaction().begin();

            em.persist(student1);
            em.persist(student2);
            em.persist(student3);


            em.persist(course1);
            em.persist(course2);
            em.persist(course3);

            em.persist(permanentInstructor);
            em.persist(visitingResearcher);
            em.persist(permanentInstructor1);

            em.getTransaction().commit();

            System.out.println("All data persisted..");
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }
    }
}
