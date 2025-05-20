package pbo.model;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @Column(name = "kode", length = 20, nullable = false)
    private String kode;

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "semester", nullable = false)
    private int semester;

    @Column(name = "credit", nullable = false)
    private int credit;

    public Course() {}

    public Course(String kode, String name, int semester, int credit) {
        this.kode = kode;
        this.name = name;
        this.semester = semester;
        this.credit = credit;
    }

    public String getKode() { return kode; }
    public void setKode(String kode) { this.kode = kode; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }

    public int getCredit() { return credit; }
    public void setCredit(int credit) { this.credit = credit; }
}