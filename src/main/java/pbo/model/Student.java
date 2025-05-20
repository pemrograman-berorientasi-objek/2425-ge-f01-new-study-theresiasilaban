package pbo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "nim", length = 20, nullable = false)
    private String nim;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "prodi", length = 50, nullable = false)
    private String prodi;

    public Student() {}

    public Student(String nim, String name, String prodi) {
        this.nim = nim;
        this.name = name;
        this.prodi = prodi;
    }

    public String getNim() { return nim; }
    public void setNim(String nim) { this.nim = nim; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getProdi() { return prodi; }
    public void setProdi(String prodi) { this.prodi = prodi; }
}