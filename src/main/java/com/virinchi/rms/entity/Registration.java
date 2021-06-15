package com.virinchi.rms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "registrations")
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "program")
    private String program;
//    @Column(name = "semester")
//    private String semester;
//    @Column(name = "created_date", insertable = false, updatable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdDate;

    public Registration() {
    }

    public Registration(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

//    public void setCreatedDate(Date createdDate) {
//        this.createdDate = createdDate;
//    }
//
//    public Date getCreatedDate() {
//        return createdDate;
//    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getProgram() {
        return program;
    }

//    public void setSemester(String semester) {
//        this.semester = semester;
//    }
//
//    public String getSemester() {
//        return semester;
//    }

}
