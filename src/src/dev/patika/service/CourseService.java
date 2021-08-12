package dev.patika.service;

import dev.patika.models.Course;
import dev.patika.repository.CrudRepository;
import dev.patika.utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class CourseService implements CrudRepository<Course> {
    EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");
    //methods
    @Override
    public List<Course> findAll() {
        return em.createQuery("select c from Course c", Course.class).getResultList();
    }

    @Override
    public Course findById(int id) {
        return em.find(Course.class, id);
    }

    @Override
    public void saveToDatabase(Course course) {
        try {
            em.getTransaction().begin();
            em.persist(course);
            em.getTransaction().commit();
            System.out.println("New Student added..");
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void deleteFromDatabase(Course object) {}
    @Override
    public void deleteFromDatabase(int id) {}

    @Override
    public void updateOnDatabase(Course course, int id) {
        try {
            em.getTransaction().begin();

            Course foundCourse = em.find(Course.class, id);
            foundCourse.setCourseCode(course.getCourseCode());
            foundCourse.setCourseName(course.getCourseName());
            foundCourse.setCreditScore(course.getCreditScore());

            em.merge(foundCourse);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }finally {
            EntityManagerUtils.closeEntityManager(em);
        }
    }

    public void deleteCourseFromDatabase(int id) {
        em.getTransaction().begin();
        Course course = em.createQuery("select c from Course c where c.id=:id", Course.class).setParameter("id", id).getSingleResult();
        em.remove(course);
        em.getTransaction().commit();
    }
}
