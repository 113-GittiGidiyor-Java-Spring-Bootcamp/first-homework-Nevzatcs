package dev.patika.service;

import dev.patika.models.Student;
import dev.patika.repository.CrudRepository;
import dev.patika.repository.StudentRepository;
import dev.patika.utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class StudentService implements CrudRepository<Student>, StudentRepository {
    EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");
    //methods
    @Override
    public List<Student> findAll() {
        return em.createQuery("select s from Student s", Student.class).getResultList();

    }

    @Override
    public Student findById(int id) {
        return em.find(Student.class, id);
    }

    @Override
    public void saveToDatabase(Student student) {
        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
            System.out.println("New Student added..");
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void deleteFromDatabase(Student object) {

    }

    @Override
    public void deleteFromDatabase(int id) {

    }

    @Override
    public void updateOnDatabase(Student student, int id) {
        try {
            em.getTransaction().begin();

            Student foundStudent = em.find(Student.class, id);
            foundStudent.setS_address(student.getS_address());
            foundStudent.setS_name(student.getS_name());
            foundStudent.setS_gender(student.getS_gender());
            foundStudent.setS_birthDate(student.getS_birthDate());
            em.merge(foundStudent);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void deleteStudentFromDatabase(String name) {
        em.getTransaction().begin();
        Student student = em.createQuery("select s from Student s where s.s_name=:s_name", Student.class).setParameter("s_name",name).getSingleResult();
        em.remove(student);
        em.getTransaction().commit();
    }
}
