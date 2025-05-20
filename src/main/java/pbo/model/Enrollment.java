package pbo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "enrollment")
public class Enrollment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "nim", referencedColumnName = "nim")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "kode", referencedColumnName = "kode")
    private Course course;

    public Enrollment() {}

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public Long getId() { return id; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}