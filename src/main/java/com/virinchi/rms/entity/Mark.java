package com.virinchi.rms.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_marks")
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "total")
    private double total;
    @Column(name = "gpa")
    private double gpa;
    @ManyToOne()
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;
    @JsonIgnore
    @OneToMany(mappedBy = "studentMark")
    private List<MarkDetail> details = new ArrayList<>();
    public Mark() {
    }

    public Mark(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public double getGpa() {
        return gpa;
    }

    public Student getStudent() {
        return student;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<MarkDetail> getDetails() {
        return details;
    }

    public void setDetails(List<MarkDetail> details) {
        this.details = details;
    }
}
