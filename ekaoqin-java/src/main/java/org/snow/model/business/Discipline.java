package org.snow.model.business;

import org.snow.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "discipline")
public class Discipline extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", length = 500, unique = false)
    @Size(min = 1, max = 500)
    private String content;

    @Column(name = "student_id")
    private Long studentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
