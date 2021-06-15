package com.virinchi.rms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_mark_details")
public class MarkDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne()
    @JoinColumn(name = "student_mark_id", referencedColumnName = "id")
    private Mark studentMark;
    @ManyToOne()
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;
    @Column(name = "mark")
    private float mark;
    @Column(name = "grade_point")
    private float gradePoint;
    
    public MarkDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mark getStudentMark() {
        return studentMark;
    }

    public void setStudentMark(Mark studentMark) {
        this.studentMark = studentMark;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public float getGradePoint() {
        return gradePoint;
    }

    public void setGradePoint(float gradePoint) {
        this.gradePoint = gradePoint;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

}
