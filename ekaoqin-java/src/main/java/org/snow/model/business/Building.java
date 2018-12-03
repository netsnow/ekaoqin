package org.snow.model.business;

import org.snow.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "building")
public class Building extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, unique = true)
    @Size(min = 1, max = 100)
    private String name;

    @Column(name = "entrance_camera", length = 300, unique = true)
    @Size(min = 0, max = 300)
    private String entranceCamera;

    @Column(name = "exit_camera", length = 300, unique = true)
    @Size(min = 0, max = 300)
    private String exitCamera;

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

    public String getEntranceCamera() {
        return entranceCamera;
    }

    public void setEntranceCamera(String entranceCamera) {
        this.entranceCamera = entranceCamera;
    }

    public String getExitCamera() {
        return exitCamera;
    }

    public void setExitCamera(String exitCamera) {
        exitCamera = exitCamera;
    }
}
