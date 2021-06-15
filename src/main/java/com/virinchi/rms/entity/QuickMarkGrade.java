package com.virinchi.rms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_quick_marks_grade")
public class QuickMarkGrade {

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
    @Column(name = "subject4_name")
    private String subject4Name;
    @Column(name = "subject5_name")
    private String subject5Name;
    @Column(name = "subject1_mark")
    private String subject1Mark;
    @Column(name = "subject2_mark")
    private String subject2Mark;
    @Column(name = "subject3_mark")
    private String subject3Mark;
    @Column(name = "subject4_mark")
    private String subject4Mark;
    @Column(name = "subject5_mark")
    private String subject5Mark;
    @Column(name = "subject1_code")
    private String subject1Code;
    @Column(name = "subject2_code")
    private String subject2Code;
    @Column(name = "subject3_code")
    private String subject3Code;
    @Column(name = "subject4_code")
    private String subject4Code;
    @Column(name = "subject5_code")
    private String subject5Code;
    @Column(name = "subject1_credit_hour")
    private int subject1CreditHour;
    @Column(name = "subject2_credit_hour")
    private int subject2CreditHour;
    @Column(name = "subject3_credit_hour")
    private int subject3CreditHour;
    @Column(name = "subject4_credit_hour")
    private int subject4CreditHour;
    @Column(name = "subject5_credit_hour")
    private int subject5CreditHour;
    @Column(name = "gpa")
    private float gpa;

    public QuickMarkGrade() {
    }

    public QuickMarkGrade(int id) {
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

    public String getSubject4Name() {
        return subject4Name;
    }

    public String getSubject5Name() {
        return subject5Name;
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

    public String getSubject4Mark() {
        return subject4Mark;
    }

    public String getSubject5Mark() {
        return subject5Mark;
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

    public void setSubject4Name(String subject4Name) {
        this.subject4Name = subject4Name;
    }

    public void setSubject5Name(String subject5Name) {
        this.subject5Name = subject5Name;
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

    public void setSubject4Mark(String subject4Mark) {
        this.subject4Mark = subject4Mark;
    }

    public void setSubject5Mark(String subject5Mark) {
        this.subject5Mark = subject5Mark;
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

    public String getSubject4Code() {
        return subject4Code;
    }

    public void setSubject4Code(String subject4Code) {
        this.subject4Code = subject4Code;
    }

    public String getSubject5Code() {
        return subject5Code;
    }

    public void setSubject5Code(String subject5Code) {
        this.subject5Code = subject5Code;
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

    public int getSubject4CreditHour() {
        return subject4CreditHour;
    }

    public void setSubject4CreditHour(int subject4CreditHour) {
        this.subject4CreditHour = subject4CreditHour;
    }

    public int getSubject5CreditHour() {
        return subject5CreditHour;
    }

    public void setSubject5CreditHour(int subject5CreditHour) {
        this.subject5CreditHour = subject5CreditHour;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public QuickMarkGrade(int id, String matricNo, String studentName, String program, String semester, String subject1Name,
            String subject2Name, String subject3Name, String subject4Name, String subject5Name, String subject1Mark,
            String subject2Mark, String subject3Mark, String subject4Mark, String subject5Mark, String subject1Code,
            String subject2Code, String subject3Code, String subject4Code, String subject5Code, int subject1CreditHour,
            int subject2CreditHour, int subject3CreditHour, int subject4CreditHour, int subject5CreditHour) {
        this.id = id;
        this.matricNo = matricNo;
        this.studentName = studentName;
        this.program = program;
        this.semester = semester;
        this.subject1Name = subject1Name;
        this.subject2Name = subject2Name;
        this.subject3Name = subject3Name;
        this.subject4Name = subject4Name;
        this.subject5Name = subject5Name;
        this.subject1Mark = subject1Mark;
        this.subject2Mark = subject2Mark;
        this.subject3Mark = subject3Mark;
        this.subject4Mark = subject4Mark;
        this.subject5Mark = subject5Mark;
        this.subject1Code = subject1Code;
        this.subject2Code = subject2Code;
        this.subject3Code = subject3Code;
        this.subject4Code = subject4Code;
        this.subject5Code = subject5Code;
        this.subject1CreditHour = subject1CreditHour;
        this.subject2CreditHour = subject2CreditHour;
        this.subject3CreditHour = subject3CreditHour;
        this.subject4CreditHour = subject4CreditHour;
        this.subject5CreditHour = subject5CreditHour;
    }

}
