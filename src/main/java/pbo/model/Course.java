package pbo.model;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table (name = "course")
public class Course {

    @Id
    @Column(name = "kode", length = 20, nullable = false)
    private String name;
    @Column(name = "name", length = 30, nullable = false)
    private String name;
    @Column(name = "semester", length = 30, nullable = false)
    private String semester;
    @Column(name = "credit", length = 30, nullable = false)
    private String credit;

    @ManyToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Student> semester;

    public Course() {
    }
    
    public Course(String name, String name, String semester) {
        this.name = name;
        this.name = name;
        this.semester = semester;
    }

    public Dorm(String name, String name, String semester, List<Student> semester) {
        this.kode = kode;
        this.name = name;
        this.semester = semester;
        this.credit = credit;
    }

    public String getKode() {
        return kode;
    }
    
    public String getName() {
        return name;
    }

    public String getCredit() {
        return credit;
    }

    public String getSemester() {
        return semester;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }
    
    public void setCredit(String credit) {
        this.credit = credit;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }    

    @Override
    public String toString() {
        return kode + "|" + name + "|" + semester + "|" + credit;
    }
}