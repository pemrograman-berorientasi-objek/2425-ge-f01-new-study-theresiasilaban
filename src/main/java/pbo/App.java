package pbo;

import pbo.model.*;
import javax.persistence.*;
import java.util.*;
import java.io.*;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        BufferedReader reader = null;
        try {
            emf = Persistence.createEntityManagerFactory("f01PU");
            em = emf.createEntityManager();
            reader = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("---")) break;

                // Tambah mahasiswa
                if (line.startsWith("student-add#")) {
                    String[] parts = line.split("#", 4);
                    if (parts.length != 4) {
                        System.err.println("Format: student-add#nim#nama#prodi");
                        continue;
                    }
                    em.getTransaction().begin();
                    try {
                        em.persist(new Student(parts[1], parts[2], parts[3]));
                        em.getTransaction().commit();
                    } catch (Exception ex) {
                        em.getTransaction().rollback();
                        System.err.println("Gagal menambah mahasiswa: " + ex.getMessage());
                    }
                }
                // Tampilkan semua mahasiswa
                else if (line.equals("student-show-all")) {
                    List<Student> students = em.createQuery("SELECT s FROM Student s ORDER BY s.nim", Student.class).getResultList();
                    for (Student s : students) {
                        System.out.println(s.getNim() + "|" + s.getName() + "|" + s.getProdi());
                    }
                }
                // Tambah mata kuliah
                else if (line.startsWith("course-add#")) {
                    String[] parts = line.split("#", 5);
                    if (parts.length != 5) {
                        System.err.println("Format: course-add#kode#nama#semester#sks");
                        continue;
                    }
                    em.getTransaction().begin();
                    try {
                        em.persist(new Course(parts[1], parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));
                        em.getTransaction().commit();
                    } catch (Exception ex) {
                        em.getTransaction().rollback();
                        System.err.println("Gagal menambah mata kuliah: " + ex.getMessage());
                    }
                }
                // Tampilkan semua mata kuliah
                else if (line.equals("course-show-all")) {
                    List<Course> courses = em.createQuery("SELECT c FROM Course c ORDER BY c.semester, c.kode", Course.class).getResultList();
                    for (Course c : courses) {
                        System.out.println(c.getKode() + "|" + c.getName() + "|" + c.getSemester() + "|" + c.getCredit());
                    }
                }
                // Enroll mahasiswa ke mata kuliah
                else if (line.startsWith("enroll#")) {
                    String[] parts = line.split("#", 3);
                    if (parts.length != 3) {
                        System.err.println("Format: enroll#nim#kode_mk");
                        continue;
                    }
                    Student s = em.find(Student.class, parts[1]);
                    Course c = em.find(Course.class, parts[2]);
                    if (s == null || c == null) {
                        System.err.println("Mahasiswa atau mata kuliah tidak ditemukan.");
                        continue;
                    }
                    Long count = em.createQuery(
                        "SELECT COUNT(e) FROM Enrollment e WHERE e.student.nim = :nim AND e.course.kode = :kode", Long.class)
                        .setParameter("nim", parts[1])
                        .setParameter("kode", parts[2])
                        .getSingleResult();
                    if (count == 0) {
                        em.getTransaction().begin();
                        try {
                            em.persist(new Enrollment(s, c));
                            em.getTransaction().commit();
                        } catch (Exception ex) {
                            em.getTransaction().rollback();
                            System.err.println("Gagal melakukan enroll: " + ex.getMessage());
                        }
                    }
                }
                // Tampilkan detail mahasiswa dan mata kuliah yang diambil
                else if (line.startsWith("student-show#")) {
                    String[] parts = line.split("#", 2);
                    if (parts.length != 2) {
                        System.err.println("Format: student-show#nim");
                        continue;
                    }
                    String nim = parts[1];
                    Student s = em.find(Student.class, nim);
                    if (s != null) {
                        System.out.println(s.getNim() + "|" + s.getName() + "|" + s.getProdi());
                        List<Course> courses = em.createQuery(
                            "SELECT e.course FROM Enrollment e WHERE e.student.nim = :nim ORDER BY e.course.semester, e.course.kode", Course.class)
                            .setParameter("nim", nim)
                            .getResultList();
                        for (Course c : courses) {
                            System.out.println(c.getKode() + "|" + c.getName() + "|" + c.getSemester() + "|" + c.getCredit());
                        }
                    } else {
                        System.err.println("Mahasiswa tidak ditemukan.");
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Terjadi kesalahan: " + e.getMessage());
        } finally {
            if (reader != null) {
                try { reader.close(); } catch (IOException e) { /* ignore */ }
            }
            if (em != null && em.isOpen()) {
                em.close();
            }
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }
}