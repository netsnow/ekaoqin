package org.snow.model.business;

import org.snow.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "student")
public class Student extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, unique = false)
    @Size(min = 1, max = 100)
    private String name;

    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "class_id")
    private Long classId;

    @Column(name = "face_sys_user_id", length = 100, unique = false)
    @Size(min = 1, max = 100)
    private String faceSysUserId;

    @Column(name = "img", length = 300, unique = false)
    @Size(min = 1, max = 300)
    private String img;

    @Column(name = "back_status")
    private boolean backStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getFaceSysUserId() {
        return faceSysUserId;
    }

    public void setFaceSysUserId(String faceSysUserId) {
        this.faceSysUserId = faceSysUserId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isBackStatus() {
        return backStatus;
    }

    public void setBackStatus(boolean backStatus) {
        this.backStatus = backStatus;
    }
}
