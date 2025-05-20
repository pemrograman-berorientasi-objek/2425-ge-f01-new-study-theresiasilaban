package pbo.f01.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.*;

public class Enrollment {
    private EntityManager entityManager;
    public Enrollment(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cleanUpTables() {
        String[] sql = {
            "DELETE FROM Student",
            "DELETE FROM Course"
        };
        entityManager.getTransaction().begin();
        for(String s : sql){
            entityManager.createQuery(s).executeUpdate();
        }
        entityManager.getTransaction().commit();  
    }

    public void addCourse(String[] data) {
        entityManager.getTransaction().begin();
        Course course = new course(data[1], data[2], data[3]);
        entityManager.persist(course);
        entityManager.getTransaction().commit();
    }

    public void addStudent(String[] data){
        entityManager.getTransaction().begin();
        Student tempStudent;
        if((tempStudent = entityManager.find(Student.class, data[1])) == null){
            Student student = new Student(data[1], data[2], data[3], data[4]);
            entityManager.persist(student);
        }else{
            if(!tempStudent.getNim().equals(data[1])){
                Student student = new Student(data[1], data[2], data[3], data[4]);
                entityManager.persist(student);
            }
        }
        entityManager.getTransaction().commit();
    }

    public void assignStudentToCourse(String[] data) {
        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class, data[1]);
        Course course = entityManager.find(course.class, data[2]);
        if (student != null && course != null && student.getProdi().equals(course.getProdi())) {
            student.getCourses().add(course);
            course.getStudents().add(student);
            course.setResident(course.getResident() + 1);
            entityManager.persist(student);
            entityManager.persist(course);
            entityManager.getTransaction().commit();
        } else {
            entityManager.getTransaction().rollback();
        }
    }

    public void displayAllcourses() {
        String courseSql = "SELECT g FROM Course g ORDER BY g.name";
        List<Course> courses = entityManager.createQuery(courseSql, course.class).getResultList();
        for (Course course : courses) {
            System.out.println(course);
            List<Student> students = course.getStudents();
            Collections.sort(students, Comparator.comparing(Student::getName));

            for (Student student : students) {
                System.out.println(student);
            }
        }
    }
}