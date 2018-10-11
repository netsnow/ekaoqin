package org.snow.model.business;

import org.snow.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "entry_log")
public class EntryLog extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "face_sys_user_id", length = 100, unique = false)
    @Size(min = 1, max = 100)
    private String faceSysUserId;

    @Column(name = "camera_id")
    private String cameraId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFaceSysUserId() {
        return faceSysUserId;
    }

    public void setFaceSysUserId(String faceSysUserId) {
        this.faceSysUserId = faceSysUserId;
    }

    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }
}
