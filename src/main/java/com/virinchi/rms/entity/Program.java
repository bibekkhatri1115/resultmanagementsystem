package com.virinchi.rms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_program")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
////    @ManyToOne()
////    @JoinColumn(name = "semester_subject_id", referencedColumnName = "id")
////    private Program semester;
//    @JoinTable(name = "tbl_program_semester", joinColumns = {
//        @JoinColumn(name = "program_id", referencedColumnName = "id"),}, inverseJoinColumns = {
//        @JoinColumn(name = "semester_id", referencedColumnName = "id")})
//    @ManyToMany(cascade = CascadeType.ALL)
//    private List<Semester> semesters = new ArrayList<>();
//    
//    @JoinTable(name = "tbl_program_semester", joinColumns = {
//        @JoinColumn(name = "semester_id", referencedColumnName = "id"),}, inverseJoinColumns = {
//        @JoinColumn(name = "subject_id", referencedColumnName = "id")})
//    @ManyToMany(cascade = CascadeType.ALL)
//    private List<Subject> subjects = new ArrayList<>();
//    
//        @JoinTable(name = "tbl_program_semester",
//        joinColumns = @JoinColumn(name = "program_id"),
//        inverseJoinColumns = @JoinColumn(name = "subject_id"))
//    @MapKeyJoinColumn(name = "semester_id")
//    @ElementCollection
//    private Map<Semester, Subject> semesterSubject = new HashMap<>();
        @OneToMany(mappedBy = "program")   
    public List<ProgramSemesterSubject> relations = new ArrayList<>();
//        
//        @OneToMany(cascade=CascadeType.ALL)
//          @JoinColumn(name = "program_id", nullable = false)
//          private Set<ProgramSemesterSubject > programSemesterSubjects;
    
    public Program() {
    }

    public Program(int id) {
        this.id = id;
    }

    public Program(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//
//    public List<Semester> getSemesters() {
//        return semesters;
//    }
//
//    public void setSemesters(List<Semester> semesters) {
//        this.semesters = semesters;
//    }

//    public void setSubjects(List<Subject> subjects) {
//        this.subjects = subjects;
//    }
//
//    public List<Subject> getSubjects() {
//        return subjects;
//    }
//
//        public void setSemesterSubject(Map<Semester, Subject> semesterSubject) {
//            this.semesterSubject = semesterSubject;
//        }
////
//    public Map<Semester, Subject> getSemesterSubject() {
//        return semesterSubject;
//    }

    public List<ProgramSemesterSubject> getRelations() {
        return relations;
    }

//    public void setProgramSemesterSubjects(Set<ProgramSemesterSubject> programSemesterSubjects) {
//        this.programSemesterSubjects = programSemesterSubjects;
//    }
//
//    public Set<ProgramSemesterSubject> getProgramSemesterSubjects() {
//        return programSemesterSubjects;
//    }

    public void setRelations(List<ProgramSemesterSubject> relations) {
        this.relations = relations;
    }

}
