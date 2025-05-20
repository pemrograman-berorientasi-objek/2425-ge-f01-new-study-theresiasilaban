package pbo.f01.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "nim", nullable = false, length = 20)
    private String nim;
    @Column(name = "name", nullable = false, length = 30)
    private String name;
    @Column(name = "prodi", nullable = false, length = 8)
    private String prodi;

    @ManyToMany(targetEntity = course.class, cascade = CascadeType.ALL)
    @JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "course_nim", referencedColumnName = "nim"), 
    inverseJoinColumns = @JoinColumn(name = "course_name", referencedColumnName = "name"))
    private List<Course> courses;


    public Student() {
    }

    public Student(String nim, String name, String prodi) {
        this.nim = nim;
        this.name = name;
        this.prodi = prodi;
    }

    public Student(String nim, String name, String prodi, List<Course> courses) {
        this.nim = nim;
        this.name = name;
        this.prodi = prodi;
        this.courses = courses;
    }

    public String getNim() {
        return nim;
    }
    
    public String getName() {
        return name;
    }
    
    public String getProdi() {
        return prodi;
    }

    public List<Course> getcourses() {
        return courses;
    }
    
    public void setNim(String nim) {
        this.nim = nim;
    }
    
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    @Override
    public String toString() {
        return nim + "|" + name + "|" + prodi;
    }
}