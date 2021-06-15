package com.virinchi.rms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_quick_marks_mba_grade")
public class QuickMarkMBAGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "matric_no")
    private String matricNo;
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "program")
    private String program;
    @Column(name = "semester")
    private String semester;
    @Column(name = "subject1_name")
    private String subject1Name;
    @Column(name = "subject2_name")
    private String subject2Name;
    @Column(name = "subject3_name")
    private String subject3Name;
    @Column(name = "subject1_mark")
    private String subject1Mark;
    @Column(name = "subject2_mark")
    private String subject2Mark;
    @Column(name = "subject3_mark")
    private String subject3Mark;
    @Column(name = "subject1_code")
    private String subject1Code;
    @Column(name = "subject2_code")
    private String subject2Code;
    @Column(name = "subject3_code")
    private String subject3Code;
    @Column(name = "subject1_credit_hour")
    private int subject1CreditHour;
    @Column(name = "subject2_credit_hour")
    private int subject2CreditHour;
    @Column(name = "subject3_credit_hour")
    private int subject3CreditHour;
    @Column(name = "gpa")
    private float gpa;

    public QuickMarkMBAGrade() {
    }

    public QuickMarkMBAGrade(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricNo() {
        return matricNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getProgram() {
        return program;
    }

    public String getSemester() {
        return semester;
    }

    public String getSubject1Name() {
        return subject1Name;
    }

    public String getSubject2Name() {
        return subject2Name;
    }

    public String getSubject3Name() {
        return subject3Name;
    }

    public String getSubject1Mark() {
        return subject1Mark;
    }

    public String getSubject2Mark() {
        return subject2Mark;
    }

    public String getSubject3Mark() {
        return subject3Mark;
    }

    public void setMatricNo(String matricNo) {
        this.matricNo = matricNo;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setSubject1Name(String subject1Name) {
        this.subject1Name = subject1Name;
    }

    public void setSubject2Name(String subject2Name) {
        this.subject2Name = subject2Name;
    }

    public void setSubject3Name(String subject3Name) {
        this.subject3Name = subject3Name;
    }

    public void setSubject1Mark(String subject1Mark) {
        this.subject1Mark = subject1Mark;
    }

    public void setSubject2Mark(String subject2Mark) {
        this.subject2Mark = subject2Mark;
    }

    public void setSubject3Mark(String subject3Mark) {
        this.subject3Mark = subject3Mark;
    }

    public String getSubject1Code() {
        return subject1Code;
    }

    public void setSubject1Code(String subject1Code) {
        this.subject1Code = subject1Code;
    }

    public String getSubject2Code() {
        return subject2Code;
    }

    public void setSubject2Code(String subject2Code) {
        this.subject2Code = subject2Code;
    }

    public String getSubject3Code() {
        return subject3Code;
    }

    public void setSubject3Code(String subject3Code) {
        this.subject3Code = subject3Code;
    }

    public int getSubject1CreditHour() {
        return subject1CreditHour;
    }

    public void setSubject1CreditHour(int subject1CreditHour) {
        this.subject1CreditHour = subject1CreditHour;
    }

    public int getSubject2CreditHour() {
        return subject2CreditHour;
    }

    public void setSubject2CreditHour(int subject2CreditHour) {
        this.subject2CreditHour = subject2CreditHour;
    }

    public int getSubject3CreditHour() {
        return subject3CreditHour;
    }

    public void setSubject3CreditHour(int subject3CreditHour) {
        this.subject3CreditHour = subject3CreditHour;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "QuickMarkMBAGrade{" + "id=" + id + ", matricNo=" + matricNo + ", studentName=" + studentName + ", program=" + program + ", semester=" + semester + ", subject1Name=" + subject1Name + ", subject2Name=" + subject2Name + ", subject1Mark=" + subject1Mark + ", subject2Mark=" + subject2Mark + ", subject3Mark=" + subject3Mark + ", subject1Code=" + subject1Code + ", subject2Code=" + subject2Code + ", subject3Code=" + subject3Code + ", subject1CreditHour=" + subject1CreditHour + ", subject2CreditHour=" + subject2CreditHour + ", subject3CreditHour=" + subject3CreditHour + ", gpa=" + gpa + '}';
    }

    public QuickMarkMBAGrade(int id, String matricNo, String studentName, String program, String semester, String subject1Name, String subject2Name, String subject3Name, String subject1Mark, String subject2Mark, String subject3Mark, String subject1Code, String subject2Code, String subject3Code, int subject1CreditHour, int subject2CreditHour, int subject3CreditHour, float gpa) {
        this.id = id;
        this.matricNo = matricNo;
        this.studentName = studentName;
        this.program = program;
        this.semester = semester;
        this.subject1Name = subject1Name;
        this.subject2Name = subject2Name;
        this.subject3Name = subject3Name;
        this.subject1Mark = subject1Mark;
        this.subject2Mark = subject2Mark;
        this.subject3Mark = subject3Mark;
        this.subject1Code = subject1Code;
        this.subject2Code = subject2Code;
        this.subject3Code = subject3Code;
        this.subject1CreditHour = subject1CreditHour;
        this.subject2CreditHour = subject2CreditHour;
        this.subject3CreditHour = subject3CreditHour;
        this.gpa = gpa;
    }

}
