package com.virinchi.rms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_program_semester")
public class ProgramSemesterSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne()
    @JoinColumn(name = "semester_id", referencedColumnName = "id")
    private Semester semester;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;
    @JsonIgnore    
    @ManyToOne()
    @JoinColumn(name = "program_id", referencedColumnName = "id")
    private Program program;

    public ProgramSemesterSubject() {
    }

    public ProgramSemesterSubject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Program getProgram() {
        return program;
    }

}
